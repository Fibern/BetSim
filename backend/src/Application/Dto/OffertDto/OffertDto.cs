using Application.Dto.OddDto;
using Domain.Entities;
using Domain.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Dto.OffertDto
{
    public class OffertDto
    {
        public string Title { get; set; }
        public BetType Type { get; set; }
        public DateTime DateTime { get; set; }
        public List<PostOddDto> Odds { get; set; }
    }
}
