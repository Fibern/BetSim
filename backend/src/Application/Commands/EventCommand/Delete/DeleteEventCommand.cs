using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Delete
{
    public record DeleteEventCommand(int id, int userId): IRequest<BaseResponse<string>>;
}
