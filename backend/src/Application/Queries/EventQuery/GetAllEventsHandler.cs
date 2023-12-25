using Application.Abstractions;
using Application.Dto.ViewModel;
using AutoMapper;
using MediatR;

namespace Application.Queries.EventQuery
{
    public class GetAllEventsHandler : IRequestHandler<GetAllEventsQuery, BaseResponse<List<EventViewModel>>>
    {
        private IEventRepository _eventRepository;
        private IMapper _mapper;

        public GetAllEventsHandler(IMapper mapper, IEventRepository eventRepository)
        {
            _mapper = mapper;
            _eventRepository = eventRepository;
        }

        public async Task<BaseResponse<List<EventViewModel>>> Handle(GetAllEventsQuery request, CancellationToken cancellationToken)
        {
            var all = await _eventRepository.GetAllAsync(request.Active);
            var allViewModel = _mapper.Map<List<EventViewModel>>(all);

            var response = new BaseResponse<List<EventViewModel>>(allViewModel, true);

            return response;
        }
    }
}
