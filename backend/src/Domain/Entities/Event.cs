using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Event
    {
        public int Id { get; set; }
        [Required]
        public string Title { get; set; }
        [Required]
        public string Icon { get; set; }
        public bool Active { get; set; } = true;
        public List<Offert>? Offerts { get; set; }
        public User Owner{ get; set; }
    }
}
