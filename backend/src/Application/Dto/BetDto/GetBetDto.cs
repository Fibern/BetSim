using Domain.Entities;
using Domain.Enums;

namespace Application.Dto.BetDto{
    public class GetBetDto
    {
        public BetStatus Status { get; set; }
        public int PredictedWinnerId { get; set; }
        public GetOffertDto Offert {get; set;}
    }
}