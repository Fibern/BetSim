using Application.Dto.OddDto;
using Application.Dto.OffertDto;
using Domain.Entities;
using Domain.Enums;

namespace Application.Dto.BetDto
{
    public class CouponGetBet
    {
        public string Title { get; set; } 
        public Status Status { get; set; }
        public DateTimeOffset DateTime { get; set; }
        public GetOddDto PredictedWinner { get; set; } 
        public string Score { get; set;}
    }
}