using Application.Dto.BetDto;

namespace Application.Dto.CouponDto
{
    public class PostCouponDto
    {
        public List<PostBetDto> Bets { get; set; }
        public double Value { get; set; }
        public int OddSum { get; set; }
        public DateTime DateTime { get; set; }
    }
}
