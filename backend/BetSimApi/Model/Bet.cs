using BetSimApi.Model.Enum;
using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Model
{
    public class Bet
    {
        public int Id { get; set; }
        [Required]
        public string Title { get; set; }
        [Required]
        public BetType Type { get; set; }
        [Required]
        public string Date { get; set; }
        public List<Odd> Odds { get; set; }
        public int EventID { get; set; }
        public string Winner { get; set; }
        public string Score { get; set; }
    }

   
}
