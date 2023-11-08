using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Model
{
    public class UserBet
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int BetId { get; set; }
        public Odd PredictedWinner { get; set; }
        [Required]
        public double Value { get; set; }

    }
}
