
using Application.Dto.OffertDto;
using MediatR;

namespace Application.Queries.UserQuery
{
    public record GetUserInfoQuery(int UserId, bool IsOddsMaker):IRequest<BaseResponse<UserInfoDto>>;

}