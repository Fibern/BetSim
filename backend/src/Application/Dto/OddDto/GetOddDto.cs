﻿using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Dto.OddDto
{
    public class GetOddDto
    {
        public int Id { get; set; }
        public string PlayerName { get; set; }
        public double OddValue { get; set; }
    }
}
