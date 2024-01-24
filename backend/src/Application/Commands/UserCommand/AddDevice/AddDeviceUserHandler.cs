using Application.Abstractions;
using MediatR;

namespace Application.Commands.UserCommand
{
    public class AddDeviceUserHandler : IRequestHandler<AddDeviceUserCommand, BaseResponse<int>>
    {
        private readonly IDeviceRepository _deviceRepository;

        public AddDeviceUserHandler(IDeviceRepository deviceRepository)
        {
            _deviceRepository = deviceRepository;
        }

        public async Task<BaseResponse<int>> Handle(AddDeviceUserCommand request, CancellationToken cancellationToken)
        {
            // check if device is already registered for this user
            bool isUserDeviceArleady = await _deviceRepository.IsUserDeviceAsync(request.UserId, request.DeviceId);
            if(isUserDeviceArleady) return new BaseResponse<int>("Is already a device of this user");

            int id = await _deviceRepository.AddAsync(request.UserId, request.DeviceId);

            return new BaseResponse<int>(id);
        }
    }
}