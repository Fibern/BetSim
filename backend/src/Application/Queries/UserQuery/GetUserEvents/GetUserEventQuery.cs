using Application.Dto.EventDto;
using MediatR;

namespace Application.Queries.UserQuery
{
    public record GetUserEventQuery(int UserId) : IRequest<BaseResponse<IReadOnlyList<GetEventDto>>>;
}