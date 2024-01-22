using Domain.Entities;
using Domain.Enums;
using Domain.UseCase;
using Microsoft.VisualBasic;

namespace UnitTests
{
    public class CouponTest
    {
        private Coupon exampleCoupon;

        public CouponTest(Coupon exampleCoupon)
        {
            exampleCoupon = new Coupon
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
                        PredictedWinnerId = 2
                    },
                    new Bet { 
                        Status = Status.Win,
                        OffertId = 2,
                        PredictedWinnerId = 3 
                        }
                },
                Value = 10.50,
                OddSum = 4.00,
                UserId = 1
            };
        }


    [Fact]
    public void UpdateBetsAndCouponsAfterScore_WhenAllBetsAreOverAndOneLose_ShouldReturnEmptyListAndUpdateOneBet()
    {
        // Arrange
        
        var offertId = 1;
        var winner = 1;

        var couponCase = new CouponCase();

        // Act
        var result = couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { exampleCoupon }, winner, offertId);

        // Assert
        Assert.NotNull(result);
        Assert.Empty(result);
    }

    [Fact]
    public void UpdateBetsAndCouponsAfterScore_WhenAllBetsAreWon_ShouldReturnListOfUsersToUpdate()
    {
        // Arrange

        var offertId = 1;
        var winner = 2;

        var couponCase = new CouponCase();

        // Act
        var result = couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { exampleCoupon }, winner, offertId);

        // Assert
        Assert.NotNull(result);
        Assert.Equal(1, result.Count);
        Assert.Equal(exampleCoupon.UserId, result[0].UserId);
        Assert.Equal(exampleCoupon.OddSum * exampleCoupon.Value, result[0].Value);
    }

    [Fact]
    public void UpdateBetsAndCouponsAfterScore_WhenAllBetsAreLost_ShouldReturnEmptyList()
    {
        // Arrange
        var coupon = new Coupon
        {
            Bets = new List<Bet>
            {
                new Bet { Status = Status.Lose },
                new Bet { Status = Status.Lose },
                new Bet { Status = Status.Lose }
            }
        };

        var offertId = 1;
        var winner = 1;

        var couponCase = new CouponCase();

        // Act
        var result = couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { coupon }, winner, offertId);

        // Assert
        Assert.NotNull(result);
        Assert.Equal(0, result.Count);
    }
    }

    
}