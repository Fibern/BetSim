using System.Security.Claims;
using Application;
using Application.Abstractions;
using Application.Dto.UserDto;
using Domain.Entities;
using Infrastructure.Repositories;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("[controller]")]
public class AccountController : ControllerBase
{
    private readonly IConfiguration _configuration;
    private readonly IUserRepository _userRepo;
    private IHttpContextAccessor _httpContextAccessor;
    private UserManager<User> _userManager;
    private int _userId;


    public AccountController(IConfiguration configuration, IUserRepository userRepo, IHttpContextAccessor httpContextAccessor, UserManager<User> userManager)
    {
        _configuration = configuration;
        _userRepo = userRepo;
        var userId = httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);
        if (userId != null) _userId = int.Parse(userId);
        _httpContextAccessor = httpContextAccessor;
        _userManager = userManager;
    }

    // [HttpPost()]
    // [Authorize]
    // public async Task<ActionResult> AddOddsMakerAsync()
    // {
    //     var user = await _userRepo.GetUserInfoAsync(_userId);
    //     IdentityResult result = await _userManager.AddToRoleAsync(user,"OddsMaker");

    //     return Ok();
    // }

    // [HttpPost("register")]
    // public async Task<IActionResult> Register([FromBody] UserRegisterDto request)
    // {
    //     var user = new User
    //     {
    //         UserName = request.Username,
    //         Email = request.Email
    //     };

    //     var result = await _userManager.CreateAsync(user, request.Password);

    //     if (result.Succeeded)
    //     {
    //         return Ok(new { Message = "Registration successful" });
    //     }

    //     return BadRequest(new { Errors = result.Errors });
    // }

    // [HttpPost("login")]
    // public async Task<IActionResult> Login([FromBody] UserLoginDto request)
    // {

    //     var result = await _signInManager.PasswordSignInAsync(request.Username, request.Password,true,false);

    //     if (result.Succeeded)
    //     {
    //         return Ok(new { Message = "Registration successful" });
    //     }

    //     return BadRequest(new { Errors = "Invalid login or password." });
    // }


    // [HttpPost("refresh")]
    // public async Task<IActionResult> Refresh([FromBody] string refreshToken)

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

    // [HttpPost("login")]
    // public async Task<ActionResult<BaseResponse<AccessTokenResponse>>> Login([FromBody] UserLoginDto request)
    // {
    //     try
    //     {
    //         CognitoUser user = new CognitoUser(request.Username,
    //         _configuration["AWS:AppClientId"], 
    //         _cognitoUserPool,
    //         _cognitoIdentityProvider);

    //         InitiateSrpAuthRequest authRequest = new InitiateSrpAuthRequest()
    //         {           
    //             Password = request.Password,
    //         };

    //         AuthFlowResponse authResponse = await user.StartWithSrpAuthAsync(authRequest);
    //         var result = authResponse.AuthenticationResult;

    //         var token = new AccessTokenResponse(){
    //             AccessToken = result.AccessToken,
    //             RefreshToken = result.RefreshToken,
    //             ExpiresIn = result.ExpiresIn,
    //             TokenType = "JwtBarer"
    //         };

    //         return Ok(new BaseResponse<AccessTokenResponse>(token));

    //     }
    //     catch (Exception error)
    //     {
    //         return Unauthorized(new BaseResponse<AccessTokenResponse>(error.Message));
    //     }
    // }


    // [HttpPost("refresh")]
    // public async Task<ActionResult<BaseResponse<AccessTokenResponse>>> Refresh([FromBody] string refreshToken)
    // {
    //     string clientId = _configuration["AWS:AppClientId"];
    //     string userPoolId = _configuration["AWS:UserPoolId"];

    //     AccessTokenResponse accessToken = await RefreshTokenAsync(refreshToken, clientId, userPoolId);


    //     if (accessToken != null)
    //     {
    //         return Ok(new BaseResponse<AccessTokenResponse>(accessToken));
    //     }
    //     else
    //     {
    //         return Unauthorized(new BaseResponse<AccessTokenResponse>("Invalid Token"));
    //     }
    // }

    // private static async Task<AccessTokenResponse> RefreshTokenAsync(string refreshToken, string clientId, string userPoolId)
    // {
    //     AmazonCognitoIdentityProviderClient providerClient = new AmazonCognitoIdentityProviderClient(RegionEndpoint.EUCentral1);

    //     InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest
    //     {
    //         AuthFlow = AuthFlowType.REFRESH_TOKEN_AUTH,
    //         AuthParameters = new Dictionary<string, string>
    //         {
    //             { "REFRESH_TOKEN", refreshToken },
    //             { "CLIENT_ID", clientId }
    //         },
    //         ClientId = clientId,
            
    //     };

    //     try
    //     {
    //         InitiateAuthResponse initiateAuthResponse = await providerClient.InitiateAuthAsync(initiateAuthRequest);

    //         if (initiateAuthResponse.AuthenticationResult != null)
    //         {
    //             return new AccessTokenResponse(){
    //                 AccessToken = initiateAuthResponse.AuthenticationResult.AccessToken,
    //                 RefreshToken = initiateAuthResponse.AuthenticationResult.RefreshToken,
    //                 ExpiresIn = initiateAuthResponse.AuthenticationResult.ExpiresIn,
    //                 TokenType = "JwtBarer"
    //             };
    //         }
    //         else
    //         {
    //             // Handle authentication failure
    //             return null;
    //         }
    //     }
    //     catch (Exception ex)
    //     {
    //         // Handle exception
    //         return null;
    //     }
    // }
}