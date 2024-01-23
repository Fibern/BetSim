using Domain.Entities;

namespace Domain.UseCase{
    public interface ICouponCase
    {
        void UpdateBetsAndCouponsAfterScore(List<Coupon> coupons,int winner, int offertId, Action<Bet, string> sendNotyfication , 
        Action<double,string> sendPoints);
    }
}