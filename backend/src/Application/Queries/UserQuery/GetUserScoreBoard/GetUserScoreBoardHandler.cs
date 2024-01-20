
using System.Collections.Generic;
using System.Reflection.Metadata.Ecma335;
using Application.Abstractions;
using Application.Dto.UserDto;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Queries.UserQuery
{
    public class GetUserScoreBoardHandler : IRequestHandler<GetUserScoreBoardQuery, BaseResponse<ScoreBoardDto>>
    {
        private readonly IUserRepository _userRepository;

        public GetUserScoreBoardHandler(IMapper mapper, IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }

        public async Task<BaseResponse<ScoreBoardDto>> Handle(GetUserScoreBoardQuery request, CancellationToken cancellationToken)
        {
            IReadOnlyList<User> users =  await _userRepository.GetUserScoreSortedAsync();

            //add place to list of users
            int place=1;
            int userPlace = 0;

            IReadOnlyList<UserScoreBoardDto> userScoreBoardDto = users
            .Select(e => 
            new UserScoreBoardDto()
            {
                Id = e.Id,
                UserName = e.UserName,
                Points = e.Points,
                Place = place++
            })
            .ToList();

            ScoreBoardDto scoreBoard = new ScoreBoardDto(){
                TopUsers = userScoreBoardDto.Take(20).ToList(),
                User =  userScoreBoardDto.FirstOrDefault(e => e.Id == request.UserId)
            };

            BaseResponse<ScoreBoardDto> result =  new BaseResponse<ScoreBoardDto>(scoreBoard);

            return result;
        }
    }
}