using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Security.Claims;
using Microsoft.AspNetCore.Identity;
using Domain.Entities.Authoryzation;

namespace Domain.Entities
{
    public class User : IdentityUser<int>
    {
        public User()
        {
            SecurityStamp = Guid.NewGuid().ToString();
        }
        public double Points { get; set; } = 500;
        public List<Event>? EventsCreated { get; set; }
        public List<Coupon>? Coupons { get; set; }
    }
}
