﻿using Application.Abstractions;
using Application.Commands.OffertCommand.Patch;
using Domain.Entities;
using Domain.UseCase;
using MediatR;
using Serilog;

namespace Application.Behaviors
{
    public class AddScorePipeline : IPipelineBehavior<PatchOffertScoreCommand, BaseResponse<string>>
    {
        private readonly ICouponRepository _couponRepository;
        private readonly ILogger _logger;

        public AddScorePipeline(ICouponRepository couponRepository, ILogger logger)
        {
            _couponRepository = couponRepository;
            _logger = logger;
        }

        public async Task<BaseResponse<string>> Handle(PatchOffertScoreCommand request, RequestHandlerDelegate<BaseResponse<string>> next, CancellationToken cancellationToken)
        {
            _logger.Information("addScorePipeline started"); 

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
