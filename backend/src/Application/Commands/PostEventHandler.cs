using Application.Abstractions;
using AutoMapper;
using BetSimApi.ViewModel;
using Domain.Entities;
using MediatR;

namespace Application.Commands
{
    public class PostEventHandler : IRequestHandler<PostEventCommand, int>
    {
        private IEventRepository _eventRepo;
        private IMapper _mapper;

        public PostEventHandler(IEventRepository dbMainContext, IMapper mapper)
        {
            _eventRepo = dbMainContext;
            _mapper = mapper;
        }

        public async Task<int> Handle(PostEventCommand request, CancellationToken cancellationToken)
        {
            Event newEvent = _mapper.Map<Event>(request);

            await _eventRepo.AddAsync(newEvent);

            return newEvent.Id;
        }
    }
}
