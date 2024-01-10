using Domain.Enums;
using System.ComponentModel.DataAnnotations;
using System.IO.Compression;

namespace Domain.Entities
{
    public class Offert
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public BetType Type { get; set; }
        public bool Active { get; set; } = true;
        public DateTimeOffset DateTime { get; set; }
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

        public void CreateTilteIfMatch(){
            if(Type == BetType.Match){
                if(Odds.Count == 2 ){
                    Title = $"{Odds[0].PlayerName} - {Odds[1].PlayerName}";
                }
            }
        }

        public double CourseToPercent(double course)
        {
            return (1 / course * 100);
        }

        public void Update(DateTimeOffset time, string title)
        {
            DateTime = time;
            Title = title;
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
