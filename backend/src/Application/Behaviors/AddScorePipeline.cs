using Application.Abstractions;
using Application.Commands.OffertCommand.Patch;
using Domain.Entities;
using Domain.UseCase;
using MediatR;
using Serilog;

namespace Application.Behaviors
{
    public class AddScorePipeline<TRequest,TResponse> : IPipelineBehavior<TRequest, TResponse> 
    where TRequest : PatchOffertScoreCommand where TResponse : BaseResponse<string>
    {
        private readonly ICouponRepository _couponRepository;
        private readonly ILogger _logger;
        private readonly IPushNotificationService _pushNotificationService;

        public AddScorePipeline(ICouponRepository couponRepository, ILogger logger, IPushNotificationService pushNotificationService)
        {
            _couponRepository = couponRepository;
            _logger = logger;
            _pushNotificationService = pushNotificationService;
        }

        public async Task<TResponse> Handle(TRequest request, RequestHandlerDelegate<TResponse> next, CancellationToken cancellationToken)
        {
            _logger.Information("addScorePipeline started"); 

            var response = await next(); 

            // if not valid
            if (!response.Succes)return response;

            List<Coupon> coupons = await _couponRepository.GetCouponsByOffert(request.Id);

            //create CouponCase to update coupons 
            var couponcase = new CouponCase();

            couponcase.UpdateBetsAndCouponsAfterScore(
                coupons, request.Winner, request.Id,
                _pushNotificationService.SendNotificationAboutNewScore,
                _pushNotificationService.SendNotyficationPointsPayload
            );
            // update coupons in database by repository
            bool succes = await _couponRepository.UpdateRangeAsync(coupons);
            response.Succes = succes;

            return response;
        }
    }
}
