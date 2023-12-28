using Application.Dto.EventDto;
using MediatR;

namespace Application.Queries.EventQuery
{
    public record GetAllEventsQuery(bool Active = true) : IRequest<BaseResponse<List<GetEventDto>>>;

}
