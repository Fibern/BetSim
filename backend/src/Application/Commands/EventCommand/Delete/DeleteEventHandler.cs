using Application.Abstractions;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Delete
{
    public class DeleteEventHandler : IRequestHandler<DeleteEventCommand, BaseResponse<string>>
    {
        private IEventRepository _eventRepo;

        public DeleteEventHandler(IEventRepository eventRepo)
        {
            _eventRepo = eventRepo;
        }

        public async Task<BaseResponse<string>> Handle(DeleteEventCommand request, CancellationToken cancellationToken)
        {
            //if not exist
            Event eventToEdit = await _eventRepo.GetById(request.Id);
            if (eventToEdit is null) return new BaseResponse<string>("Event not found");

            //update
            eventToEdit.TogleActive();
            bool sucess = await _eventRepo.UpdateAsync(eventToEdit);


            return new BaseResponse<string>(sucess);
        }
    }
}
