using BetSimApi.Model.Enum;
using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Model
{
    public class Event
    {
        public int Id { get; set; }
        [Required]
        public string Title { get; set; }
        [Required]
        public string Icon { get; set; }
        public User Owner { get; set; }
        public List<Bet> Bets  { get; set; }
    }
}
