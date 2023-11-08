using BetSimApi.Model;
using Microsoft.EntityFrameworkCore;

namespace BetSimApi
{
    public class DbMainContext : DbContext
    {
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

        }

        public DbSet<User> User { get; set; }
        public DbSet<Bet> Bet { get; set; }
        public DbSet<Odd> Odd { get; set; }
        public DbSet<User> Event { get; set; }
    }
}
