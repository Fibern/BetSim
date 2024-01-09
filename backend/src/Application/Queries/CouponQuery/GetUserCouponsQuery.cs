using Application.Dto.CouponDto;
using MediatR;

namespace Application.Queries.CouponQuery
{
    public record GetUserCouponQuery(int UserId) : IRequest<BaseResponse<IReadOnlyList<GetCouponDto>>> ;
}