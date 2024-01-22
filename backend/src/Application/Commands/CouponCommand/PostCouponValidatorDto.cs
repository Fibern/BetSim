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
                .GreaterThan(0)
                .LessThanOrEqualTo(10.000)
                .WithMessage("Value must be between 0 and 10.000");
            RuleFor(p => p.OddSum * p.Value).LessThanOrEqualTo(1000000)
                .WithMessage("Max possible winner sum is 1 000 000");
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