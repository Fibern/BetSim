using Application.Abstractions;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Patch
{
    public class PatchOffertScoreHandler : IRequestHandler<PatchOffertScoreCommand, BaseResponse<string>>
    {
        private IOffertRepository _offertRepo;

        public PatchOffertScoreHandler(IOffertRepository offertRepo)
        {
            _offertRepo = offertRepo;
        }

        public async Task<BaseResponse<string>> Handle(PatchOffertScoreCommand request, CancellationToken cancellationToken)
        {
            Offert offert = await _offertRepo.GetUserOffert(request.Id, request.UserId);

            var validator = new PatchOffertScoreValidator(offert.ValidateWinner);
            var validationResult = validator.Validate(request);

            //if invalid
            if(validationResult.IsValid == false) return new BaseResponse<string>(validationResult);

            //if offert not exist
            if (offert == null) return new BaseResponse<string>("offert not found in yours offerts");

            //if offert is not active
            if (offert.Active == false) return new BaseResponse<string>("offert is not active",false);

            //update score
            offert.AddScore(request.Winner, request.Score);

            bool succes = await _offertRepo.UpdateAsync(offert);

            return new BaseResponse<string>(succes);
        }
    }
}
