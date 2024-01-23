using Application.Dto.BetDto;
using Domain.Entities;
using Domain.Enums;

namespace Application.Dto.CouponDto
{
    public class GetCouponDto
    {
        public List<CouponGetBet> Bets { get; set; }
        public double Value { get; set; }
        public int OddSum { get; set; }
        public DateTimeOffset DateTime { get; set; }
        public Status status { get; set; }

    }
}

