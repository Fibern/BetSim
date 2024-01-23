using Domain.Entities;
using Domain.Enums;
using Domain.UseCase;
using Microsoft.VisualBasic;

namespace UnitTests
{
    public class CouponTest : IClassFixture<CouponFixture>
    {
        private Coupon exampleCoupon;

        public CouponTest(CouponFixture couponFixture)
        {
            exampleCoupon = couponFixture.ExampleCoupon;
        }


        [Fact]
        public void UpdateBetsAndCouponsAfterScore_WhenAllBetsAreOverAndOneLose_ShouldUpdateBet()
        {
            // Arrange
            
            var offertId = 1;
            var winner = 1;

            var couponCase = new CouponCase();

            exampleCoupon.Bets[0].Status = Status.InProgress;
            exampleCoupon.Bets[1].Status = Status.Win;

            // Act
            couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { exampleCoupon }, winner, offertId);

            // Assert
            Assert.Equal(exampleCoupon.Bets[0].Status , Status.Lose);
            Assert.Equal(exampleCoupon.Status , Status.Lose);
        }

        [Fact]
        public void UpdateBetsAndCouponsAfterScore_WhenAllBetsAreWinning_ShouldUpdateUserPoints()
        {
            // Arrange

            var offertId = 1;
            var winner = 2;

            var couponCase = new CouponCase();

            exampleCoupon.Bets[0].Status = Status.InProgress;
            exampleCoupon.Bets[1].Status = Status.Win;

            // Act
            couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { exampleCoupon }, winner, offertId);

            // Assert
            Assert.Equal(exampleCoupon.Bets[0].Status , Status.Win);
            Assert.Equal(exampleCoupon.Status , Status.Win);
            Assert.Equal(exampleCoupon.User.Points , 542.21);
        }

        [Fact]
        public void UpdateBetsAndCouponsAfterScore_WhenSomeBetsInProgres_ShouldUpdateOneBet()
        {
            // Arrange

            var offertId = 1;
            var winner = 2;

            var couponCase = new CouponCase();

            exampleCoupon.Bets[1].Status = Status.InProgress;

            // Act
            couponCase.UpdateBetsAndCouponsAfterScore(new List<Coupon> { exampleCoupon }, winner, offertId);

            // Assert
            Assert.Equal(exampleCoupon.Bets[0].Status , Status.Win);
            Assert.Equal(exampleCoupon.User.Points , 500);
            Assert.Equal(exampleCoupon.Status , Status.InProgress);
        }
    }

    
}