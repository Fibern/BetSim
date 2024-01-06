﻿using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Coupon
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public User User { get; set; }
        public List<Bet> Bets { get; set; }
        public double Value { get; set; }
        public int OddSum { get; set; }
        public DateTime DateTime { get; set; }

    }
}
