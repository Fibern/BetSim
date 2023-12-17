using Application.ViewModel;
using MediatR;

namespace Application.Queries
{
    public record GetAllEventsQuery() : IRequest<List<EventViewModel>>
    {

    }
}
