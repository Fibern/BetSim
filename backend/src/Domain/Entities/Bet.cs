using Domain.Enums;

namespace Domain.Entities
{
    public class Bet
    {
        public int Id { get; set; }
        public BetStatus Status { get; set; }
        public int OffertId { get; set; }
        public Offert Offert { get; set; }
        public int PredictedWinnerId { get; set; }
        public Odd PredictedWinner { get; set; }
        public int CouponId { get; set; }
        public Coupon Coupon { get; set; }
    }
}
