using Domain.Enums;
using System.ComponentModel.DataAnnotations;
using System.IO.Compression;

namespace Domain.Entities
{
    public class Offert
    {
        public int Id { get; set; }
        public BetType Type { get; set; }
        public bool Active { get; set; } = true;
        public DateTime DateTime { get; set; }
        public List<Odd> Odds { get; set; }
        public int EventId { get; set; }    
        public Event Event { get; set; }
        public int? Winner { get; set; } = -1;
        public string? Score { get; set; } = "";

        public bool ValidateOdds()
        {
            var sum = Odds.Sum(e => this.CourseToPercent(e.OddValue));
            if (Math.Abs(sum - 100) > 1) return false;
            return true;

        }

        public bool ValidateWinner()
        {
            return (Winner < Odds.Count);
        }

        public double CourseToPercent(double course)
        {
            return (1 / course * 100);
        }

        public void Update(DateTime time)
        {
            DateTime = time;
        }

        public void AddScore(int winner, string score)
        {
            Winner = winner;
            Score = score;
            Active = false;
        }

        public void Archive()
        {
            Active = false;
        }

    }
}
