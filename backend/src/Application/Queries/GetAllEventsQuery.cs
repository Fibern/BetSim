using BetSimApi.Model;
using BetSimApi.ViewModel;
using MediatR;

namespace BetSimApi.Queries
{
    public record GetAllEventsQuery() : IRequest<List<EventViewModel>>
    {

    }
}
