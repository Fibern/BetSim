using Domain.Entities;

namespace Application.Dto.CouponDto
{
    public class GetCouponDto
    {
        public List<Bet> Bets { get; set; }
        public double Value { get; set; }
        public int OddSum { get; set; }
        public DateTime DateTime { get; set; }

    }
}

