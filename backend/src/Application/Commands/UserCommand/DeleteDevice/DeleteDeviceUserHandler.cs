using Application.Abstractions;
using MediatR;

namespace Application.Commands.UserCommand
{
    public class DeleteDeviceUserHandler : IRequestHandler<DeleteDeviceUserCommand, BaseResponse<string>>
    {
        private readonly IDeviceRepository _deviceRepository;

        public DeleteDeviceUserHandler(IDeviceRepository deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }

        public async Task<BaseResponse<string>> Handle(DeleteDeviceUserCommand request, CancellationToken cancellationToken)
        {
            // check if device is already registered for this user

            _deviceRepository.Delete(request.UserId, request.DeviceId);

            return new BaseResponse<string>();
        }
    }
}