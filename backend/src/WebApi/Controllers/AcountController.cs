using System.Security.Claims;
using Amazon;
using Amazon.CognitoIdentityProvider;
using Amazon.CognitoIdentityProvider.Model;
using Amazon.Extensions.CognitoAuthentication;
using Application;
using Application.Abstractions;
using Application.Dto.UserDto;
using Domain.Entities;
using Infrastructure.Repositories;
using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("[controller]")]
public class AccountController : ControllerBase
{
    private readonly IAmazonCognitoIdentityProvider _cognitoIdentityProvider;
    private readonly CognitoUserPool _cognitoUserPool;
    private readonly IConfiguration _configuration;
    private readonly IUserRepository _userRepo;


    public AccountController(CognitoUserPool cognitoUserPool, IAmazonCognitoIdentityProvider cognitoIdentityProvider, IConfiguration configuration, IUserRepository userRepo)
    {
        _cognitoUserPool = cognitoUserPool;
        _cognitoIdentityProvider = cognitoIdentityProvider;
        _configuration = configuration;
        _userRepo = userRepo;
    }

    [HttpPost("register")]
    public async Task<ActionResult<BaseResponse<string>>> Register([FromBody] UserRegisterDto request)
    {

        //validation

        var signUpRequest = new SignUpRequest
        {
            ClientId = _configuration["AWS:AppClientId"],
            Password = request.Password,
            Username = request.Username
        };

        signUpRequest.UserAttributes.Add(new AttributeType
        {
            Name = "email",
            Value = request.Email
        });

        signUpRequest.UserAttributes.Add(new AttributeType
        {
            Name = "",
            Value = request.Username
        });

        try{
            SignUpResponse response = await _cognitoIdentityProvider.SignUpAsync(signUpRequest);

            // response.UserSub;
            var user = new User(){
                UserName = request.Username,
                Email = request.Email
            };

            _userRepo.AddAsync(user);
     
            return Ok(new BaseResponse<string>("User Registered Successfully!", true));
        }
        catch(Exception error){
            return BadRequest(new BaseResponse<string>(error.Message));
        }
    }

    // [HttpPost("confirmEmail")]
    // public async Task<ActionResult<BaseResponse<string>>> PostAsync([FromBody] EmailConfirmationDto UserRequest)
    // {
    //     ConfirmSignUpRequest request = new ConfirmSignUpRequest
    //     {
    //         ClientId = _configuration["AWS:AppClientId"],
    //         ConfirmationCode = UserRequest.ConfirmationCode,
    //         Username = UserRequest.Email
    //     };

    //     try
    //     {
    //         var response = await _cognitoIdentityProvider.ConfirmSignUpAsync(request);

    //         return Ok(new BaseResponse<string>("Email confirmed",true));
    //     }
    //     catch (CodeMismatchException)
    //     {
    //         return new UserSignUpResponse
    //         {
    //             IsSuccess = false,
    //             Message = "Invalid Confirmation Code",
    //             EmailAddress = model.EmailAddress
    //         };
    //     }
    // }

    [HttpPost("login")]
    public async Task<ActionResult<BaseResponse<AccessTokenResponse>>> Login([FromBody] UserLoginDto request)
    {
        try
        {
            CognitoUser user = new CognitoUser(request.Username,
            _configuration["AWS:AppClientId"], 
            _cognitoUserPool,
            _cognitoIdentityProvider);

            InitiateSrpAuthRequest authRequest = new InitiateSrpAuthRequest()
            {           
                Password = request.Password,
            };

            AuthFlowResponse authResponse = await user.StartWithSrpAuthAsync(authRequest);
            var result = authResponse.AuthenticationResult;

            var token = new AccessTokenResponse(){
                AccessToken = result.AccessToken,
                RefreshToken = result.RefreshToken,
                ExpiresIn = result.ExpiresIn,
                TokenType = "JwtBarer"
            };

            return Ok(new BaseResponse<AccessTokenResponse>(token));

        }
        catch (Exception error)
        {
            return Unauthorized(new BaseResponse<AccessTokenResponse>(error.Message));
        }
    }


    [HttpPost("refresh")]
    public async Task<ActionResult<BaseResponse<AccessTokenResponse>>> Refresh([FromBody] string refreshToken)
    {
        string clientId = _configuration["AWS:AppClientId"];
        string userPoolId = _configuration["AWS:UserPoolId"];

        AccessTokenResponse accessToken = await RefreshTokenAsync(refreshToken, clientId, userPoolId);


        if (accessToken != null)
        {
            return Ok(new BaseResponse<AccessTokenResponse>(accessToken));
        }
        else
        {
            return Unauthorized(new BaseResponse<AccessTokenResponse>("Invalid Token"));
        }
    }

    private static async Task<AccessTokenResponse> RefreshTokenAsync(string refreshToken, string clientId, string userPoolId)
    {
        AmazonCognitoIdentityProviderClient providerClient = new AmazonCognitoIdentityProviderClient(RegionEndpoint.EUCentral1);

        InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest
        {
            AuthFlow = AuthFlowType.REFRESH_TOKEN_AUTH,
            AuthParameters = new Dictionary<string, string>
            {
                { "REFRESH_TOKEN", refreshToken },
                { "CLIENT_ID", clientId }
            },
            ClientId = clientId,
            
        };

        try
        {
            InitiateAuthResponse initiateAuthResponse = await providerClient.InitiateAuthAsync(initiateAuthRequest);

            if (initiateAuthResponse.AuthenticationResult != null)
            {
                return new AccessTokenResponse(){
                    AccessToken = initiateAuthResponse.AuthenticationResult.AccessToken,
                    RefreshToken = initiateAuthResponse.AuthenticationResult.RefreshToken,
                    ExpiresIn = initiateAuthResponse.AuthenticationResult.ExpiresIn,
                    TokenType = "JwtBarer"
                };
            }
            else
            {
                // Handle authentication failure
                return null;
            }
        }
        catch (Exception ex)
        {
            // Handle exception
            return null;
        }
    }
}