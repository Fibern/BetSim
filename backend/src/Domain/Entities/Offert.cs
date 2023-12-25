using Domain.Enums;
using System.ComponentModel.DataAnnotations;
using System.IO.Compression;

namespace Domain.Entities
{
    public class Offert
    {
        public int Id { get; set; }
        [Required]
        public string Title { get; set; }
        public BetType Type { get; set; }
        public bool Active { get; set; } = true;
        public DateTime DateTime { get; set; }
        public List<Odd> Odds { get; set; }
        public int EventId { get; set; }    
        public Event Event { get; set; }
        public int Winner { get; set; }
        public string Score { get; set; }

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

        public void Update(string title,DateTime time, List<Odd> odds)
        {
            Title = title;
            DateTime = time;
            Odds = odds;
        }

        public void AddScore(int winner, string score)
        {
            Winner = winner;
            Score = score;
        }

        public void Archive()
        {
            Active = false;
        }

    }
}
