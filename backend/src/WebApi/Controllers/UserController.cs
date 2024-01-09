
using System.Security.Claims;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private IHttpContextAccessor _httpContextAccessor;
        private readonly IMediator _mediator;
        private int _userId;

        public UserController(IMediator mediator, IHttpContextAccessor httpContextAccessor)
        {
            _mediator = mediator;
            var userId = httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);
            if (userId != null) _userId = int.Parse(userId);
            _httpContextAccessor = httpContextAccessor;
        }
    }
}