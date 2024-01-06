using MediatR;

namespace Application.Queries.CouponQuery
{
    public record GetUserCouponQuery(int userId) : IRequest<> ;
}