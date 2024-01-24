using MediatR;

namespace Application.Commands.UserCommand
{
    public record DeleteDeviceUserCommand(int UserId, string DeviceId) : IRequest<BaseResponse<string>>;
}