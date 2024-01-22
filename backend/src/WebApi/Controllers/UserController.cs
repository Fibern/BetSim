
using System.Security.Claims;
using Application;
using Application.Commands.UserCommand;
using Application.Dto;
using Application.Dto.CouponDto;
using Application.Dto.EventDto;
using Application.Dto.UserDto;
using Application.Queries.CouponQuery;
using Application.Queries.UserQuery;
using Domain.Entities;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
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

        [HttpGet]
        [Authorize]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<UserInfoDto>>>> GetUserInfo()
        {
            bool isOddsMaker = _httpContextAccessor.HttpContext.User.IsInRole("OddsMaker");
            var command = new GetUserInfoQuery(_userId,isOddsMaker);
            var response = await _mediator.Send(command);

            if(response.Succes = true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPost("AddDeviceToken")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<int>>> PostAsync([FromBody] PostTokenDto postToken)
        {
            var command = new AddDeviceUserCommand(_userId, postToken.TokenId);
            var response = await _mediator.Send(command);

            if(response.Succes = true) return Ok(response);

            return BadRequest(response);
        }

        [HttpDelete("DeleteDeviceToken")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<string>>> DeleteAsync([FromBody] PostTokenDto postToken)
        {
            var command = new DeleteDeviceUserCommand(_userId, postToken.TokenId);
            var response = await _mediator.Send(command);

            if(response.Succes = true) return Ok(response);

            return BadRequest(response);
        }

        [HttpGet("Event")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetEventDto>>>> GetUserEventAsync()
        {
            var command = new GetUserEventQuery(_userId);
            var response = await _mediator.Send(command);

            if(response.Succes = true) return Ok(response);

            return BadRequest(response);
        }


        [HttpGet("Coupon")]
        [Authorize]    
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetCouponDto>>>> GetAsync()

        {
            var command = new GetUserCouponQuery(_userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpGet("scoreBoard")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<ScoreBoardDto>>> GetScoreboardAsync()
        {
            var command = new GetUserScoreBoardQuery(_userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPut()]
        [Authorize]
        public async Task<ActionResult<BaseResponse<string>>> PutAsync(UserPutDto userPutDto)
        {
            var command = new PutUserCommand(_userId, userPutDto);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpDelete()]
        [Authorize]
        public async Task<ActionResult<BaseResponse<string>>> DeleteAsync()
        {
            var command =  new DeleteUserCommand(_userId);
            
            var response = await _mediator.Send(command);
            if (response.Succes == true) return Ok(response);
            return BadRequest(response);
        }
    }
}