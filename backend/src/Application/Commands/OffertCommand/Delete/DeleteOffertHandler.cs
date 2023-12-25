using Application.Abstractions;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Delete
{
    public class DeleteOffertHandler : IRequestHandler<DeleteOffertCommand, BaseResponse<string>>
    {
        private IOffertRepository _ofertRepo;

        public DeleteOffertHandler(IOffertRepository ofertRepo)
        {
            _ofertRepo = ofertRepo;
        }

        public async Task<BaseResponse<string>> Handle(DeleteOffertCommand request, CancellationToken cancellationToken)
        {
            var offert = await _ofertRepo.GetUserOffert(request.Id, request.UserId);
            offert.Archive();

            if (offert == null) return new BaseResponse<string>("Offert not found in yours offerts");
            bool succes = await _ofertRepo.UpdateAsync(offert);

            return new BaseResponse<string>(succes);
        }
    }
}
