using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Event
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Icon { get; set; }
        public bool Active { get; private set; } = true;           
        public List<Offert>? Offerts { get; set; }
        public int OwnerId { get; set; }
        public User Owner{ get; set; }


        public void Update(string title, string icon)
        {
            Title = title;
            Icon = icon;
        }

        public void Archive()
        {
            Active = false;
        }


    }
}
