using Microsoft.AspNetCore.Identity;

namespace BetSimApi.Model
{
    public class User: IdentityUser
    {
        public User()
        {
            SecurityStamp = Guid.NewGuid().ToString();
        }
        public new int Id { get; set; }
        public double Points { get; set; } = 500;
        public List<Event>? EventsCreated { get; set; }
        public List<Coupon>? Coupons { get; set; }

    }
}
