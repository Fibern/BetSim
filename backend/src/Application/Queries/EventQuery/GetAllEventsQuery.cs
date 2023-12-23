using Application.ViewModel;
using MediatR;

namespace Application.Queries.EventQuery
{
    public record GetAllEventsQuery() : IRequest<BaseResponse<List<EventViewModel>>>
    {
        public bool Active { get; set; } = true;
    }
}
