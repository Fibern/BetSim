using Application.Abstractions;
using AutoMapper;
using BetSimApi.Model;
using BetSimApi.ViewModel;
using MediatR;
using System.Collections.ObjectModel;

namespace BetSimApi.Queries
{
    public class GetAllEventsHandler : IRequestHandler<GetAllEventsQuery, List<EventViewModel>>
    {
        private IEventRepository _eventRepository;
        private IMapper _mapper;

        public GetAllEventsHandler(IMapper mapper,IEventRepository eventRepository)
        {
            _mapper = mapper;
            _eventRepository = eventRepository;
        }

        public async Task<List<EventViewModel>> Handle(GetAllEventsQuery request, CancellationToken cancellationToken)
        {
            var all = await _eventRepository.GetAllAsync();
            return _mapper.Map<List<EventViewModel>>(all);
        }
    }
}
