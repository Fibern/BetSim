using Application.Abstractions;
using Application.Dto.CouponDto;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Commands.CouponCommand
{
    public class PostCouponHandler : IRequestHandler<PostCouponCommand, BaseResponse<int>>
    {
        private ICouponRepository _CouponRepo;
        private IOddsRepository _OddsRepo; 
        private IMapper _mapper;

        public PostCouponHandler(ICouponRepository dbMainContext, IMapper mapper, IOddsRepository oddsRepo)
        {
            _CouponRepo = dbMainContext;
            _mapper = mapper;
            _OddsRepo = oddsRepo;
        }

        public async Task<BaseResponse<int>> Handle(PostCouponCommand request, CancellationToken cancellationToken)
        {
           

            //download odds for validation
            var offertsIds = request.CouponDto.Bets.Select(e => e.PredictedWinnerId).ToList();
            var odds = await _OddsRepo.GetOddsByListOfIdAsync(offertsIds);

            var validator = new PostCouponValidatorDto(odds.Sum(e => e.OddValue));
            var validationResult = await validator.ValidateAsync(request.CouponDto);
            var response = new BaseResponse<int>(validationResult);

            if (!validationResult.IsValid) return response;

            Coupon newCoupon =  _mapper.Map<Coupon>(request.CouponDto);

            await _CouponRepo.AddAsync(newCoupon);
            response.Message = newCoupon.Id;

            return response;
        }
    }
}
