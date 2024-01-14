using Application.Dto.UserDto;
using Domain.Entities;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("[controller]")]
public class AccountController : ControllerBase
{
    private readonly UserManager<User> _userManager;
    private readonly SignInManager<User> _signInManager;

    public AccountController(UserManager<User> userManager, SignInManager<User> signInManager)
    {
        _userManager = userManager;
        _signInManager = signInManager;
    }

    [HttpPost("register")]
    public async Task<IActionResult> Register([FromBody] UserRegisterDto request)
    {
        var user = new User
        {
            UserName = request.Username,
            Email = request.Email
        };

        var result = await _userManager.CreateAsync(user, request.Password);

        if (result.Succeeded)
        {
            return Ok(new { Message = "Registration successful" });
        }

        return BadRequest(new { Errors = result.Errors });
    }

    [HttpPost("login")]
    public async Task<IActionResult> Login([FromBody] UserLoginDto request)
    {

        var result = await _signInManager.PasswordSignInAsync(request.Username, request.Password,true,false);

        if (result.Succeeded)
        {
            return Ok(new { Message = "Registration successful" });
        }

        return BadRequest(new { Errors = "Invalid login or password." });
    }


    // [HttpPost("refresh")]
    // public async Task<IActionResult> Refresh([FromBody] string refreshToken)
    // {

    //     var result = await _userManager.Token

    //     if (result.Succeeded)
    //     {
    //         return Ok(new { Message = "Registration successful" });
    //     }

    //     return BadRequest(new { Errors = "Invalid login or password." });
    // }



}
