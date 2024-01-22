using Domain.Entities;

namespace Domain.UseCase{
    public interface ICouponCase
    {
        List<UsersToUpdate> UpdateBetsAndCouponsAfterScore(List<Coupon> coupons,int winner, int offertId);
    }
}