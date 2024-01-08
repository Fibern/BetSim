using Application.Dto.CouponDto;
using FluentValidation;

namespace Application.Commands.CouponCommand
{
    public class PostCouponValidatorDto : AbstractValidator<PostCouponDto>
    {
        public PostCouponValidatorDto()
        {
            RuleFor(p => p.Value)
                .NotEmpty()
                .GreaterThan(0);
            RuleFor(p => p.OddSum)
                .Must(p => true);
        }


    }
}