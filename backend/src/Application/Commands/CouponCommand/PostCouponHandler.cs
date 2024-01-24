using Application.Abstractions;
using Application.Dto.CouponDto;
using AutoMapper;
using Domain.Entities;
using MediatR;

namespace Application.Commands.CouponCommand
{
    public class PostCouponHandler : IRequestHandler<PostCouponCommand, BaseResponse<double>>
    {
        private ICouponRepository _CouponRepo;
        private IUserRepository _UserRepo;
        private IOddsRepository _OddsRepo; 
        private IMapper _mapper;

        public PostCouponHandler(ICouponRepository dbMainContext, IMapper mapper, IOddsRepository oddsRepo, IUserRepository userRepo)
        {
            _CouponRepo = dbMainContext;
            _mapper = mapper;
            _OddsRepo = oddsRepo;
            _UserRepo = userRepo;
        }

        public async Task<BaseResponse<double>> Handle(PostCouponCommand request, CancellationToken cancellationToken)
        {
           

            //download odds for validation
            var offertsIds = request.CouponDto.Bets.Select(e => e.PredictedWinnerId).ToList();
            var odds = await _OddsRepo.GetOddsByListOfIdAsync(offertsIds);

            // calculate correct oddSum 
            double oddSum = 1; 
            foreach (var item in odds)
            {
                oddSum *= item.OddValue;
            }

            //validation
            var validator = new PostCouponValidatorDto(oddSum);
            var validationResult = await validator.ValidateAsync(request.CouponDto);
            var response = new BaseResponse<double>(validationResult);

            if (!validationResult.IsValid) return response;

            //check if user have enough points
            var user = await  _UserRepo.GetUserInfoAsync(request.UserId);

            if(user.Points < request.CouponDto.Value){
                return new BaseResponse<double>("Użytkownik nie posiada wymagajej ilości punktów");
            }

            //add coupon 
            Coupon newCoupon =  _mapper.Map<Coupon>(request.CouponDto);
            newCoupon.UserId = request.UserId;

            await _CouponRepo.AddAsync(newCoupon);

            //delete user points
            user.Points -= newCoupon.Value;
            _UserRepo.PutAsync(user);


            response.Message = user.Points;
            response.Succes = true;

            return response;
        }
    }
}
