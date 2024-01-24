using System.ComponentModel.DataAnnotations;
using Domain.Enums;

namespace Domain.Entities
{
    public class Coupon
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public Status Status{ get; set; } = Status.InProgress;
        public User User { get; set; }
        public List<Bet> Bets { get; set; }
        public double Value { get; set; }
        public double OddSum { get; set; }
        public DateTimeOffset DateTime { get; set; }


            public Double CalculateOddSum()
            {
               return Bets.Sum(e => e.Offert.Odds.Select(z => z.OddValue).FirstOrDefault(e.PredictedWinnerId) );
            }

    }
}
