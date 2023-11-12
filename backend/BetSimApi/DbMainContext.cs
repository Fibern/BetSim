using BetSimApi.Model;
using Microsoft.EntityFrameworkCore;
using System.Runtime.InteropServices;

namespace BetSimApi
{
    public class DbMainContext : DbContext
    {
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            //Optional configuration
        }

        public DbSet<User> User { get; set; }
        public DbSet<Offert> Bet { get; set; }
        public DbSet<Odd> Odd { get; set; }
        public DbSet<Offert> Offert { get; set; }
        public DbSet<User> Event { get; set; }
    }
}
