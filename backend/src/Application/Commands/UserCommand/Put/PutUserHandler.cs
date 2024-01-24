using Application;
using Application.Abstractions;
using Domain.Entities;
using MediatR;

namespace Application.Commands.UserCommand{
    public class PutUserHandler : IRequestHandler<PutUserCommand, BaseResponse<string>>
    {
        private readonly IUserRepository _userRepo;

        public PutUserHandler(IUserRepository userRepo)
        {
            _userRepo = userRepo;
        }

        public async Task<BaseResponse<string>> Handle(PutUserCommand request, CancellationToken cancellationToken)
        {

            User user = await _userRepo.GetUserInfoAsync(request.UserId);
            try{
                user.UserName = request.UserPut.UserName;
                bool sucess = await _userRepo.PutAsync(user);
                return new BaseResponse<string>(sucess);
            }
            catch(Exception e)
            {
                return new BaseResponse<string>("Username already exist",false);
            }

            
        }
    }
}
