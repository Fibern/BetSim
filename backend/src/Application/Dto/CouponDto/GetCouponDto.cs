using Application.Dto.BetDto;
using Domain.Entities;

namespace Application.Dto.CouponDto
{
    public class GetCouponDto
    {
        public List<CouponGetBet> Bets { get; set; }
        public double Value { get; set; }
        public int OddSum { get; set; }
        public DateTimeOffset DateTime { get; set; }

    }
}

