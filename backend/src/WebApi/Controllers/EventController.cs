using Application;
using Application.Commands.EventCommand.Delete;
using Application.Commands.EventCommand.Post;
using Application.Commands.EventCommand.Put;
using Application.Queries;
using Application.Queries.EventQuery;
using Application.ViewModel;
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
            var userId = httpContextAccessor.HttpContext.User.FindFirst(ClaimTypes.NameIdentifier).Value;
            _userId = int.Parse(userId);
            _httpContextAccessor = httpContextAccessor;
        }

        [HttpGet]
        public async Task<ActionResult<IReadOnlyList<EventViewModel>>> GetAllEvents([FromQuery] GetAllEventsQuery request)
        {
            var response = await _mediator.Send(request);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

        [HttpPost]
        [Authorize]
        public async Task<ActionResult<BaseResponse<int>>> PostEvent(string title, string icon)
        {

            PostEventCommand command = new PostEventCommand( title, icon, _userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(await _mediator.Send(response));
        }

        [HttpPut("{id}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<int>>> PutEvent(string title, string icon, [FromRoute] int id)
        {

            var command = new PutEventCommand(id,_userId, title,icon);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(await _mediator.Send(response));
        }


        [HttpDelete("{id}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<string>>> Delete([FromRoute] int id)
        {
            var command = new DeleteEventCommand(id,_userId);
            var response = await _mediator.Send(command);

            if (response.Succes == true) return Ok(response);

            return BadRequest(response);
        }

    }
}
