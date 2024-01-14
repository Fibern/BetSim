
using Application.Dto.UserDto;
using MediatR;

namespace Application.Queries.UserQuery
{
    public class GetUserScoreBoardHandler : IRequestHandler<GetUserScoreBoardQuery, BaseResponse<UserScoreBoardDto>>
    {
        public Task<BaseResponse<UserScoreBoardDto>> Handle(GetUserScoreBoardQuery request, CancellationToken cancellationToken)
        {
            throw new NotImplementedException();
        }
    }
}