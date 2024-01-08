using Application.Abstractions;
using Application.Dto.OffertDto;
using AutoMapper;
using MediatR;

namespace Application.Queries.OffertQuery
{
    public class GetOffertByEventHandler : IRequestHandler<GetOffertByEventQuery, BaseResponse<IReadOnlyList<GetOffertDto>>>
    {
        private IOffertRepository _ofertRepo;
        private IMapper _mapper;

        public GetOffertByEventHandler(IOffertRepository ofertRepo, IMapper mapper)
        {
            _ofertRepo = ofertRepo;
            _mapper = mapper;
        }

        public async Task<BaseResponse<IReadOnlyList<GetOffertDto>>> Handle(GetOffertByEventQuery request, CancellationToken cancellationToken)
        {
            var offerts = await _ofertRepo.GetEventOffertAsync(request.EventId);
            var offertView = _mapper.Map<IReadOnlyList<GetOffertDto>>(offerts);

            return new BaseResponse<IReadOnlyList<GetOffertDto>>(offertView,true);
        }
    }
}
