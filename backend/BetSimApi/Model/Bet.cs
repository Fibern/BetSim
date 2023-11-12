using BetSimApi.Model.Enums;

namespace BetSimApi.Model
{
    public class Bet
    {
        public int Id { get; set; }
        public BetStatus status { get; set; }
        public Offert OffertId { get; set; }
        public Odd PredictedWinner { get; set; }
        public Coupon? Coupon { get; set; }
    }
}
