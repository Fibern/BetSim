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

            //add title and score to GetCouponDto
            for (int i = 0; i < all.Count(); i++)
            {
                for (int j = 0; j < all[i].Bets.Count(); j++)
                {
                    allViewModel[i].Bets[j].Title = all[i].Bets[j].PredictedWinner.Offert.Title;
                    allViewModel[i].Bets[j].Score = all[i].Bets[j].PredictedWinner.Offert.Score;
                    allViewModel[i].Bets[j].DateTime = all[i].Bets[j].PredictedWinner.Offert.DateTime;
                }
            }

            var response = new BaseResponse<IReadOnlyList<GetCouponDto>>(allViewModel, true);

            return response;
        }
    }
}
