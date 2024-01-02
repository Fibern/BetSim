using Application.Abstractions;
using Application.Dto.OffertDto;
using AutoMapper;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.OffertQuery
{
    public class GetOffertHandler : IRequestHandler<GetOffertQuery, BaseResponse<IReadOnlyList<GetOffertDto>>>
    {
        private IOffertRepository _ofertRepo;
        private IMapper _mapper;

        public GetOffertHandler(IOffertRepository ofertRepo, IMapper mapper)
        {
            _ofertRepo = ofertRepo;
            _mapper = mapper;
        }

        public async Task<BaseResponse<IReadOnlyList<GetOffertDto>>> Handle(GetOffertQuery request, CancellationToken cancellationToken)
        {
            var offerts = await _ofertRepo.GetAllAsync(request.dateTime);
            var offertView = _mapper.Map<IReadOnlyList<GetOffertDto>>(offerts);

            return new BaseResponse<IReadOnlyList<GetOffertDto>>(offertView,true);
        }
    }
}
