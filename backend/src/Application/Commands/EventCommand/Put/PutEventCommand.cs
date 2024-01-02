using Application.Dto.EventDto;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Put
{
    public record PutEventCommand(EventDto EventPut, int Id,int UserId) : IRequest<BaseResponse<int>>;
}
