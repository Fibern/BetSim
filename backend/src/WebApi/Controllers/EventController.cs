using Application;
using Application.Commands.EventCommand.Delete;
using Application.Commands.EventCommand.Post;
using Application.Commands.EventCommand.Put;
using Application.Dto.EventDto;
using Application.Queries;
using Application.Queries.EventQuery;
using Domain.Entities;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics.Tracing;
using System.Drawing;
using System.Security.Claims;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EventController : ControllerBase
    {
        private IHttpContextAccessor _httpContextAccessor;
        private readonly IMediator _mediator;
        private int _userId;

        public EventController(IMediator mediator, IHttpContextAccessor httpContextAccessor)
        {
            _mediator = mediator;
            var userId = httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);
             if(userId != null)_userId = int.Parse(userId);
            _httpContextAccessor = httpContextAccessor;
        }

        [HttpGet]
        public async Task<ActionResult<IReadOnlyList<GetEventDto>>> GetAllEvents([FromQuery] GetAllEventsQuery request)
        {
            var response = await _mediator.Send(request);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPost]
        [Authorize(Roles = "OddsMaker")]
        public async Task<ActionResult<BaseResponse<int>>> PostEvent(EventDto eventDto)
        {

            PostEventCommand command = new PostEventCommand( eventDto, _userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPut("{id}")]
        [Authorize(Roles = "OddsMaker") ]
        public async Task<ActionResult<BaseResponse<int>>> PutEvent(EventDto eventDto, [FromRoute] int id)
        {

            var command = new PutEventCommand(eventDto, id, _userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(await _mediator.Send(response));
        }


        [HttpDelete("{id}")]
        [Authorize(Roles = "OddsMaker")]
        public async Task<ActionResult<BaseResponse<string>>> Delete([FromRoute] int id)
        {
            var command = new DeleteEventCommand(id,_userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

    }
}
