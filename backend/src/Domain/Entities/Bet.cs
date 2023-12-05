using Domain.Enums;

namespace Domain.Entities
{
    public class Bet
    {
        public int Id { get; set; }
        public BetStatus Status { get; set; }
        public Offert Offert { get; set; }
        public Odd PredictedWinner { get; set; }
        public Coupon? Coupon { get; set; }
    }
}
