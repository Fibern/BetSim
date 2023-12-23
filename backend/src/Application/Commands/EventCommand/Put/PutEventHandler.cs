using Application.Abstractions;
using AutoMapper;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Put
{
    internal class PutEventHandler : IRequestHandler<PutEventCommand, BaseResponse<int>>
    {
        private IEventRepository _eventRepo;

        public PutEventHandler(IEventRepository dbMainContext, IMapper mapper)
        {
            _eventRepo = dbMainContext;
        }

        public async Task<BaseResponse<int>> Handle(PutEventCommand request, CancellationToken cancellationToken)
        {
            var validator = new PutEventValidator();
            var validationResult = await validator.ValidateAsync(request);

            //if not valid
            if (!validationResult.IsValid) return new BaseResponse<int>(validationResult);

            //if not exist
            Event eventToEdit = await _eventRepo.GetById(request.Id);
            if (eventToEdit is null) return new BaseResponse<int>("Event not found");

            //update
            eventToEdit.Update(request.Title, request.Icon);
            bool sucess = await _eventRepo.UpdateAsync(eventToEdit);


            return new BaseResponse<int>(sucess);
        }
    }
}
