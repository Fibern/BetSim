using BetSimApi.Commands;
using BetSimApi.Queries;
using BetSimApi.Queries.Handlers;
using BetSimApi.ViewModel;
using MediatR;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics.Tracing;

namespace BetSimApi.Controllers
{
    public class EventController:ControllerBase
    {
        private readonly IMediator _mediator;

        public EventController(IMediator mediator)
        {
            _mediator = mediator;
        }

        [HttpGet]
        public async Task<List<EventViewModel>> GetAllEvents()
        {
            var command = new GetAllEventsQuery();
            return await _mediator.Send(command);
        }

        [HttpPost]
        public async Task<int> PostEvent(PostEventCommand request)
        {
            return await _mediator.Send(request);
        }

    }
}
