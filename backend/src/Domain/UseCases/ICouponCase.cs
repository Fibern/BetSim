using Domain.Entities;

namespace Domain.UseCase{
    public interface ICouponCase
    {
        void UpdateBetsAndCouponsAfterScore(List<Coupon> coupons,int winner, int offertId);
    }
}