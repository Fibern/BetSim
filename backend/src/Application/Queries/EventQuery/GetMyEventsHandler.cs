using Application.Abstractions;
using Application.Dto.EventDto;
using AutoMapper;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.EventQuery
{
    internal class GetMyEventsHandler : IRequestHandler<GetMyEventsQuery, BaseResponse<IReadOnlyList<GetEventDto>>>
    {
        private IEventRepository _eventRepository;
        private IMapper _mapper;
        public GetMyEventsHandler(IMapper mapper, IEventRepository eventRepository)
        {
            _mapper = mapper;
            _eventRepository = eventRepository;
        }

        public async Task<BaseResponse<IReadOnlyList<GetEventDto>>> Handle(GetMyEventsQuery request, CancellationToken cancellationToken)
        {
            var my = await _eventRepository.GetAllMyAsync(request.UserId);
            var myView = _mapper.Map<IReadOnlyList<GetEventDto>>(my);
            var response = new BaseResponse<IReadOnlyList<GetEventDto>>(myView,true);

            return response;
        }
    }
}
