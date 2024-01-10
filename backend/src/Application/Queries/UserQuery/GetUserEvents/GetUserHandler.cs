using Application.Abstractions;
using Application.Dto.EventDto;
using AutoMapper;
using MediatR;

namespace Application.Queries.UserQuery
{
    public class GetUserEventHandler : IRequestHandler<GetUserEventQuery, BaseResponse<IReadOnlyList<GetEventDto>>>
    {
        IEventRepository _eventRepo;
        IMapper _mapper;

        public GetUserEventHandler(IEventRepository eventRepo, IMapper mapper)
        {
            _eventRepo = eventRepo;
            _mapper = mapper;
        }

        public async Task<BaseResponse<IReadOnlyList<GetEventDto>>> Handle(GetUserEventQuery request, CancellationToken cancellationToken)
        {
            var events = await _eventRepo.GetAllMyAsync(request.UserId);
            var eventsDto = _mapper.Map<IReadOnlyList<GetEventDto>>(events); 

            return new BaseResponse<IReadOnlyList<GetEventDto>>(eventsDto);
        }
    }
}