using Application.Dto.OffertDto;
using MediatR;

namespace Application.Queries.UserQuery
{
    public record GetUserOffertQuery(int UserId) : IRequest<BaseResponse<IReadOnlyList<GetOffertDto>>>;
}