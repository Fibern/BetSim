using Application.Abstractions;
using Application.Commands.OffertCommand.Patch;
using Domain.Entities;
using Domain.UseCase;
using MediatR;

namespace Application.Behaviors
{
    public class AddScorePipeline : IPipelineBehavior<PatchOffertScoreCommand, BaseResponse<string>>
    {
        private readonly ICouponRepository _couponRepository;

        public AddScorePipeline(ICouponRepository couponRepository)
        {
            _couponRepository = couponRepository;
        }

        public async Task<BaseResponse<string>> Handle(PatchOffertScoreCommand request, RequestHandlerDelegate<BaseResponse<string>> next, CancellationToken cancellationToken)
        {
            BaseResponse<string> response = await next(); 

            // if not valid
            if (!response.Succes)return response;

            List<Coupon> coupons = await _couponRepository.GetCouponsByOffert(request.Id);

            var couponcase = new CouponCase();
            couponcase.UpdateBetsAndCouponsAfterScore(coupons, request.Winner, request.Id);

            bool succes = await _couponRepository.UpdateRangeAsync(coupons);
            response.Succes = succes;
            

            return response;
        }
    }
}
