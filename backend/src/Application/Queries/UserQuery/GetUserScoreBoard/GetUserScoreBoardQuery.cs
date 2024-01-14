using Application.Dto.OffertDto;
using Application.Dto.UserDto;
using MediatR;

namespace Application.Queries.UserQuery
{
    public record GetUserScoreBoardQuery(int UserId):IRequest<BaseResponse<UserScoreBoardDto>>;

}