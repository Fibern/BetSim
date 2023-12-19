using Application.ViewModel;
using MediatR;

namespace Application.Queries.EventQuery
{
    public record GetAllEventsQuery() : IRequest<BaseResponse<List<EventViewModel>>>
    {
        public bool active { get; set; } = true;
    }
}
