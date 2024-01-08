using Application.Abstractions;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Commands.CouponCommand
{
    public class PostCouponHandler : IRequestHandler<PostCouponCommand, BaseResponse<int>>
    {
        private ICouponRepository _CouponRepo;


        public PostCouponHandler(ICouponRepository dbMainContext)
        {
            _CouponRepo = dbMainContext;
        }

        public async Task<BaseResponse<int>> Handle(PostCouponCommand request, CancellationToken cancellationToken)
        {
            var validator = new PostCouponValidatorDto();
            var validationResult = await validator.ValidateAsync(request.CouponDto);
            var response = new BaseResponse<int>(validationResult);

            if (!validationResult.IsValid) return response;

            await _CouponRepo.AddAsync(newEvent);
            response.Message = newEvent.Id;

            return response;
        }
    }
}
