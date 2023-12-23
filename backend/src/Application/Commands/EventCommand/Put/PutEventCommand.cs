using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Put
{
    public record PutEventCommand(int Id,int UserId,string Title, string Icon) : IRequest<BaseResponse<int>>;
}
