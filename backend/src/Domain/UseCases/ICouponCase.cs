using Domain.Entities;

namespace Domain.UseCase{
    public interface ICouponCase
    {
        void UpdateBetsAndCouponsAfterScore(List<Coupon> coupons,int winner, int offertId, 
        Func<Bet,string,Task> sendNotyfication , 
        Func<double,string, Task> sendPoints);
    }
}