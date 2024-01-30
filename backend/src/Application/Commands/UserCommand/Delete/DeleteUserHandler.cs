using System.Runtime.CompilerServices;
using Application.Abstractions;
using MediatR;

namespace Application.Commands.UserCommand
{
    public class DeleteUserHandler : IRequestHandler<DeleteUserCommand, BaseResponse<string>>
    {
        private IUserRepository _userRepo;

        public DeleteUserHandler(IUserRepository userRepo)
        {
            _userRepo = userRepo;
        }

        public async Task<BaseResponse<string>> Handle(DeleteUserCommand request, CancellationToken cancellationToken)
        {
            await _userRepo.Delete(request.UserId);

           return new BaseResponse<string>("",true);
        }
    }
}