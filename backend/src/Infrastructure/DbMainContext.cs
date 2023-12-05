using Domain.Entities;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System.Runtime.InteropServices;

namespace Infrastructure
{
    public class DbMainContext : IdentityDbContext<User>        
    {

        public DbMainContext(DbContextOptions<DbMainContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            //Optional configuration
            modelBuilder.Entity<User>().HasMany(e => e.EventsCreated).WithMany(e => e.Administrators);
        }

        public DbSet<User> User { get; set; }
        public DbSet<Bet> Bet { get; set; }
        public DbSet<Odd> Odd { get; set; }
        public DbSet<Offert> Offert { get; set; }
        public DbSet<Event> Event { get; set; }
        public DbSet<Coupon> Coupon { get; set; }
    }
}
