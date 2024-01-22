using System.Collections;
using Domain.Entities;
using Domain.Enums;

namespace Domain.UseCase{
    public record UsersToUpdate(int UserId, double Value);
    public class CouponCase : ICouponCase
    {
        
        public List<UsersToUpdate> UpdateBetsAndCouponsAfterScore(List<Coupon> coupons,int winner, int offertId)
        {
            List<UsersToUpdate> usersToUpdate = new List<UsersToUpdate>();
            foreach (var coupon in coupons)
            {
                int betWin = 0; 
                int betsOver = 0;
                // update bets
                foreach(var bet in coupon.Bets){

                    if(bet.OffertId == offertId){
                        if(bet.PredictedWinnerId == winner){
                            bet.Status = Status.Win;
                        }
                        else{
                            bet.Status = Status.Lose;
                        }
                    }
                    
                    // calculate how many bets are over
                    if(bet.Status != Status.InProgress)betsOver++;

                    // calculate how many bets are win
                    if(bet.Status == Status.Win)betWin++;;
                }

                //update user points if coupon is over and win
                if(betsOver == coupon.Bets.Count()){
                    if(betWin == betsOver){
                        coupon.Status = Status.Win;
                        //update user points
                        coupon.User.Points += coupon.OddSum * coupon.Value;
                    }
                    else{
                        coupon.Status = Status.Lose;
                    }
                }
            }
            return usersToUpdate;
        }
    }
}