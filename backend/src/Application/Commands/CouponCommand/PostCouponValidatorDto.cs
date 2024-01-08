using Application.Dto.CouponDto;
using Domain.Entities;
using FluentValidation;

namespace Application.Commands.CouponCommand
{
    public class PostCouponValidatorDto : AbstractValidator<PostCouponDto>
    {
        public PostCouponValidatorDto(double corectSum)
        {
            RuleFor(p => p.Value)
                .NotEmpty()
                .GreaterThan(0);
            RuleFor(p => p.DateTime)
                .NotEmpty();
            RuleFor(p => p.Bets.Count)
                .GreaterThan(0)
                .WithMessage("coupon must have at least one bet");
            RuleFor(p => Math.Abs(p.OddSum - corectSum)).LessThan(0.1)
                .WithMessage("Wrong total sum of bets");
            
        }

        

    }
}