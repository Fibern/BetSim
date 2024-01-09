using MediatR;

namespace Application.Commands.UserCommand
{
    public record DeleteUserCommand(int UserId) : IRequest<BaseResponse<string>>;

}