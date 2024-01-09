
using Application.Dto.CouponDto;
using MediatR;

namespace Application.Commands.CouponCommand
{
   public record PostCouponCommand(int UserId, PostCouponDto CouponDto):IRequest<BaseResponse<int>>;
}
