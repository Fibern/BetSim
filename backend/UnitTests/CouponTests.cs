using Domain.Entities;

namespace UnitTests
{
    public class CouponTest
    {
        [Fact]
        public void TestName()
        {
            // act
            Coupon testcoupon = new Coupon(){
                Bets = 
                [
                    new Bet(){

                        PredictedWinnerId = 0,
                        PredictedWinner = new Odd(){
                            OddValue = 2
                        }
                    },

                    new Bet(){
                        PredictedWinnerId = 1,
                        PredictedWinner = new Odd(){
                            OddValue = 3
                        }
                    }       
                ]
            };

            // arrange

            // assertion
        }  
    }

    
}