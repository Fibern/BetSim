using Application.Abstractions;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Commands.EventCommand.Post
{
    public class PostEventHandler : IRequestHandler<PostEventCommand, BaseResponse<int>>
    {
        private IEventRepository _eventRepo;
        private IMapper _mapper;

        public PostEventHandler(IEventRepository dbMainContext, IMapper mapper)
        {
            _eventRepo = dbMainContext;
            _mapper = mapper;
        }

        public async Task<BaseResponse<int>> Handle(PostEventCommand request, CancellationToken cancellationToken)
        {
            var validator = new PostEventValidator();
            var validationResult = await validator.ValidateAsync(request);
            var response = new BaseResponse<int>(validationResult);

            if (!validationResult.IsValid) return response;

            Event newEvent = _mapper.Map<Event>(request);
            await _eventRepo.AddAsync(newEvent);
            response.Message = newEvent.Id;

            return response;
        }
    }
}
