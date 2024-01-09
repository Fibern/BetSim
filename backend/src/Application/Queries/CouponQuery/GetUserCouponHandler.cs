using Application.Abstractions;
using Application.Dto.CouponDto;
using Application.Queries.CouponQuery;
using AutoMapper;
using MediatR;

namespace Application.Queries.CouponQuery
{
    public class GetUserCouponHandler : IRequestHandler<GetUserCouponQuery, BaseResponse<IReadOnlyList<GetCouponDto>>>
    {
        private ICouponRepository _CouponRepository;
        private IMapper _mapper;

        public GetUserCouponHandler(IMapper mapper, ICouponRepository eventRepository)
        {
            _mapper = mapper;
            _CouponRepository = eventRepository;
        }

        public async Task<BaseResponse<IReadOnlyList<GetCouponDto>>> Handle(GetUserCouponQuery request, CancellationToken cancellationToken)
        {
            var all = await _CouponRepository.GetAsync(request.UserId);
            var allViewModel = _mapper.Map<IReadOnlyList<GetCouponDto>>(all);

            var response = new BaseResponse<IReadOnlyList<GetCouponDto>>(allViewModel, true);

            return response;
        }
    }
}
