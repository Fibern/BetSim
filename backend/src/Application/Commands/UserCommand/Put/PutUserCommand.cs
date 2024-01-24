using Application;
using Application.Dto.UserDto;
using MediatR;

namespace Application.Commands.UserCommand
{
    public record PutUserCommand(int UserId , UserPutDto UserPut):IRequest<BaseResponse<string>>;
}

