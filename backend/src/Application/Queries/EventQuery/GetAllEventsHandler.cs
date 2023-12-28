using Application.Abstractions;
using AutoMapper;
using MediatR;

namespace Application.Queries.EventQuery
{
    public class GetAllEventsHandler : IRequestHandler<GetAllEventsQuery, BaseResponse<List<GetEventDto>>>
    {
        private IEventRepository _eventRepository;
        private IMapper _mapper;

        public GetAllEventsHandler(IMapper mapper, IEventRepository eventRepository)
        {
            _mapper = mapper;
            _eventRepository = eventRepository;
        }

        public async Task<BaseResponse<List<GetEventDto>>> Handle(GetAllEventsQuery request, CancellationToken cancellationToken)
        {
            var all = await _eventRepository.GetAllAsync(request.Active);
            var allViewModel = _mapper.Map<List<GetEventDto>>(all);

            var response = new BaseResponse<List<GetEventDto>>(allViewModel, true);

            return response;
        }
    }
}
