using Application.Abstractions;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Commands.EventCommand.Post
{
    public class PostEventHandler : IRequestHandler<PostEventCommand, BaseResponse<int>>
    {
        private IEventRepository _eventRepo;


        public PostEventHandler(IEventRepository dbMainContext)
        {
            _eventRepo = dbMainContext;
        }

        public async Task<BaseResponse<int>> Handle(PostEventCommand request, CancellationToken cancellationToken)
        {
            var validator = new PostEventValidator();
            var validationResult = await validator.ValidateAsync(request);
            var response = new BaseResponse<int>(validationResult);

            if (!validationResult.IsValid) return response;

            Event newEvent = new Event()
            {
                Icon = request.Icon,
                Title = request.Title,
                OwnerId = request.OwnerId
            };

            await _eventRepo.AddAsync(newEvent);
            response.Message = newEvent.Id;

            return response;
        }
    }
}
