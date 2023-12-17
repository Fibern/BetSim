using Application.Commands.EventCommand;
using Application.Queries;
using Application.ViewModel;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics.Tracing;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EventController : ControllerBase
    {
        private readonly IMediator _mediator;

        public EventController(IMediator mediator)
        {
            _mediator = mediator;
        }

        [HttpGet]
        public async Task<ActionResult<List<EventViewModel>>> GetAllEvents()
        {
            var command = new GetAllEventsQuery();
            return  Ok(await _mediator.Send(command));
        }

        [HttpPost]
        [Authorize]
        public async Task<ActionResult<int>> PostEvent(PostEventCommand request)
        {
            return  Ok(await _mediator.Send(request));
        }

    }
}
