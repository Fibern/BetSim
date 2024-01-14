using Application.Abstractions;
using Application.Dto.UserDto;
using Domain.Entities;
using MediatR;

namespace Application.Queries.UserQuery
{
    public class GetUserInfoHandler : IRequestHandler<GetUserInfoQuery, BaseResponse<UserInfoDto>>
    {
        IUserRepository _userrepo;
        public GetUserInfoHandler(IUserRepository userrepo)
        {
            _userrepo = userrepo;
        }

        public async Task<BaseResponse<UserInfoDto>> Handle(GetUserInfoQuery request, CancellationToken cancellationToken)
        {
            User user = await _userrepo.GetUserInfoAsync(request.UserId); 

            var userInfo = new UserInfoDto()
            {
                IsOddsMaker = request.IsOddsMaker,
                Email = user.Email,
                Points = user.Points
            };


            return new BaseResponse<UserInfoDto>(userInfo);
        }
    }

}