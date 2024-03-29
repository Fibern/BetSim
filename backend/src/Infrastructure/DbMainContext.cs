﻿using Domain.Entities;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System.Runtime.InteropServices;

namespace Infrastructure
{
    public class DbMainContext : IdentityDbContext<User,IdentityRole<int>,int>        
    {

        public DbMainContext(DbContextOptions<DbMainContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<User>().HasIndex(u => u.UserName).IsUnique();

            //if admin was deleted events still exist
            modelBuilder.Entity<User>().HasMany(u => u.EventsCreated)
            .WithOne(u => u.Owner).OnDelete(DeleteBehavior.Restrict);
            
        }
        
        public DbSet<User> User { get; set; }
        public DbSet<Bet> Bet { get; set; }
        public DbSet<Odd> Odd { get; set; }
        public DbSet<Offert> Offert { get; set; }
        public DbSet<Event> Event { get; set; }
        public DbSet<Coupon> Coupon { get; set; }
        public DbSet<DeviceToken> DeviceToken { get; set; }
    }
}
