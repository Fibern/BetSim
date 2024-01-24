using MediatR;

namespace Application.Commands.UserCommand
{
    public record AddDeviceUserCommand(int UserId, string DeviceId) : IRequest<BaseResponse<int>>;
}