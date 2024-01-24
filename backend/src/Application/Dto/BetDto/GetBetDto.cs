using Application.Dto.OffertDto;
using Domain.Entities;
using Domain.Enums;

namespace Application.Dto.BetDto{
    public class GetBetDto
    {
        public Status Status { get; set; }
        public int PredictedWinnerId { get; set; }
        public GetOffertDto Offert {get; set;}
    }
}