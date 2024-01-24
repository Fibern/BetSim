using Application.Abstractions;
using Application.Dto.OffertDto;
using AutoMapper;
using MediatR;

namespace Application.Queries.UserQuery
{
    public class GetUserOffertHandler : IRequestHandler<GetUserOffertQuery, BaseResponse<IReadOnlyList<GetOffertDto>>>
    {
        private readonly IOffertRepository _offerRepository;
        private readonly IMapper _mapper;

        public GetUserOffertHandler(IOffertRepository offerRepository, IMapper mapper)
        {
            _offerRepository = offerRepository;
            _mapper = mapper;
        }

        public async Task<BaseResponse<IReadOnlyList<GetOffertDto>>> Handle(GetUserOffertQuery request, CancellationToken cancellationToken)
        {
            var allOferts = await _offerRepository.GetAllUserOffert(request.UserId);
            IReadOnlyList<GetOffertDto> offertList = _mapper.Map<IReadOnlyList<GetOffertDto>>(allOferts);

            return new BaseResponse<IReadOnlyList<GetOffertDto>>(offertList,true); 

        }
    }
}