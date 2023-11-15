using BetSimApi.Commands;
using BetSimApi.Handlers;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace BetSimApi.Controllers
{
    public class AuthorizationController : ControllerBase
    {
        //    private readonly IMediator _mediator;

        //    public AuthorizationController(IMediator mediator)
        //    {
        //        _mediator = mediator;
        //    }

        //    [HttpPost]
        //    public async Task<IActionResult> LoginUser(LoginCommand reqest)
        //    {
        //        var command = new LoginCommand(reqest);

        //        return await _mediator.Send(command);
        //    }
    }

}
