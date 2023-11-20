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
        public List<Offert>? Offerts  { get; set; }
        public List<User>? Administrators { get; set; }
    }
}
