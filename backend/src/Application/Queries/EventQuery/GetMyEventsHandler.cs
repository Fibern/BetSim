using Application.Abstractions;
using Application.ViewModel;
using AutoMapper;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.EventQuery
{
    internal class GetMyEventsHandler : IRequestHandler<GetMyEventsQuery, BaseResponse<IReadOnlyList<EventViewModel>>>
    {
        private IEventRepository _eventRepository;
        private IMapper _mapper;
        public GetMyEventsHandler(IMapper mapper, IEventRepository eventRepository)
        {
            _mapper = mapper;
            _eventRepository = eventRepository;
        }

        public async Task<BaseResponse<IReadOnlyList<EventViewModel>>> Handle(GetMyEventsQuery request, CancellationToken cancellationToken)
        {
            var my = await _eventRepository.GetAllMyAsync(request.UserId);
            var myView = _mapper.Map<IReadOnlyList<EventViewModel>>(my);
            var response = new BaseResponse<IReadOnlyList<EventViewModel>>(myView,true);

            return response;
        }
    }
}
