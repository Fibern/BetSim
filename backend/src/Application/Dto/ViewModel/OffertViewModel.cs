﻿using Domain.Entities;
using Domain.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Dto.ViewModel
{
    public class OffertViewModel
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public BetType Type { get; set; }
        public bool Active { get; set; }
        public DateTime DateTime { get; set; }
        public List<OddViewModel> Odds { get; set; }
        public int Winner { get; set; }
        public string Score { get; set; }
    }
}
