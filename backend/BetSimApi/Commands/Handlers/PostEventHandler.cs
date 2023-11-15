using AutoMapper;
using BetSimApi.Model;
using BetSimApi.ViewModel;
using MediatR;

namespace BetSimApi.Commands.Handlers
{
    public class PostEventHandler : IRequestHandler<PostEventCommand, int>
    {
        private DbMainContext _dbContext;
        private IMapper _mapper;

        public PostEventHandler(DbMainContext dbMainContext, IMapper mapper)
        {
            _dbContext = dbMainContext;
            _mapper = mapper;
        }

        public async Task<int> Handle(PostEventCommand request, CancellationToken cancellationToken)
        {
            Event newEvent = _mapper.Map<Event>(request);

            await _dbContext.Event.AddAsync(newEvent);
            await _dbContext.SaveChangesAsync();

            return newEvent.Id;
        }
    }
}
