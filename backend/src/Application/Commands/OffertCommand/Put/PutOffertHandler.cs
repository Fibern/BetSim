using Application.Abstractions;
using AutoMapper;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Put
{
    public class PutOffertHandler : IRequestHandler<PutOffertCommand, BaseResponse<int>>
    {
        private IMapper _mapper;
        private IOffertRepository _oferRepo;

        public PutOffertHandler(IMapper mapper, IOffertRepository oferRepo)
        {
            _mapper = mapper;
            _oferRepo = oferRepo;
        }

        public async Task<BaseResponse<int>> Handle(PutOffertCommand request, CancellationToken cancellationToken)
        {
            var offert = await _oferRepo.GetUserOffert(request.id,request.userId);

            //if not exist
            if (offert == null) return new BaseResponse<int>("Ofert not found in yours oferts");

            
            var validator = new PutOffertValidator(offert.ValidateOdds);
            var validationResult = validator.Validate(request);

            //if not valid
            if(!validationResult.IsValid)return new BaseResponse<int>(validationResult);

            //update
            offert.Update(request.Date);
            bool succes = await _oferRepo.UpdateAsync(offert);


            return new BaseResponse<int>(succes);
        }
    }
}
