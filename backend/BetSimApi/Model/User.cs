using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Model
{
    public class User
    {
        public int Id { get; set; }
        [Required]
        public string Login { get; set; }
        [Required]
        public string Email { get; set; }
        public string Role { get; set; }
        public double Points { get; set; } = 0;
        [Required]
        public string Password { get; set; }
        public byte[] PasswordHash { get; set; } = new byte[0];
        public byte[] PasswordSalt { get; set; } = new byte[0];
        public List<Event>? EventsCreated { get; set; }
        public List<Coupon>? Coupons { get; set; }

    }
}
