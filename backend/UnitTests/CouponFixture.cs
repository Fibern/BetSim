using Domain.Entities;
using Domain.Enums;

namespace UnitTests{
    public class CouponFixture : IClassFixture<Coupon>
    {
        public Coupon ExampleCoupon;

        public CouponFixture()
        {
            ExampleCoupon = new Coupon
            {
                Bets = new List<Bet>
                {
                    new Bet { 
                        Status = Status.InProgress,
                        OffertId = 1,
                        PredictedWinnerId = 2
                    },
                    new Bet { 
                        Status = Status.Win,
                        OffertId = 3,
                        PredictedWinnerId = 4
                    },
                    new Bet { 
                        Status = Status.Win,
                        OffertId = 2,
                        PredictedWinnerId = 3 
                        }
                },
                Value = 10.50,
                OddSum = 4.02,
                User = new User(){
                    Id=1,
                    Points = 500
                },
                Status = Status.InProgress,
                

            };

            var sendNotyfication = (Bet bet,string token) => Task.CompletedTask;
            var sendDataPayload = (double points,string token) => Task.CompletedTask;
        }
    }
}