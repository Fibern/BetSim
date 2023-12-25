using MediatR;
using Microsoft.AspNetCore.Mvc;
using System.Security.Claims;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class OffertController :ControllerBase
    {
        private IHttpContextAccessor _httpContextAccessor;
        private readonly IMediator _mediator;
        private int _userId;

        public OffertController(IMediator mediator, IHttpContextAccessor httpContextAccessor)
        {
            _mediator = mediator;
            var userId = httpContextAccessor.HttpContext.User.FindFirst(ClaimTypes.NameIdentifier).Value;
            _userId = int.Parse(userId);
            _httpContextAccessor = httpContextAccessor;
        }

        
    }
}
