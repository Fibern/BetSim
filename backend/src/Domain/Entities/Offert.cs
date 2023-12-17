﻿using Domain.Enums;
using System.ComponentModel.DataAnnotations;

namespace Domain.Entities
{
    public class Offert
    {
        public int Id { get; set; }
        [Required]
        public string Title { get; set; }
        [Required]
        public BetType Type { get; set; }
        [Required]
        public string Date { get; set; }
        public List<Odd> Odds { get; set; }
        public Event Event { get; set; }
        public string Winner { get; set; }
        public string Score { get; set; }
    }


}
