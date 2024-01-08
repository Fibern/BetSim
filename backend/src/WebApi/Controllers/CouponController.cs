using System.Security.Claims;
using Application;
using Application.Commands.CouponCommand;
using Application.Dto.CouponDto;
using Application.Queries.CouponQuery;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace WebApi.Controllers
{
    class CouponController : ControllerBase
    {
        private int _userId;
        private readonly IMediator _mediator;

        public CouponController(IHttpContextAccessor httpContextAccessor, IMediator mediator)
        {
            _mediator = mediator;
             var userId = httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);
             if(userId != null)_userId = int.Parse(userId);
        }

        [HttpGet]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetCouponDto>>>> GetAsync()
        {
            var command = new GetUserCouponQuery(_userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPost]
        public async Task<ActionResult<BaseResponse<int>>> PostAsync(PostCouponDto coupon)
        {
            var command = new PostCouponCommand(_userId, coupon);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }
        
    }
}