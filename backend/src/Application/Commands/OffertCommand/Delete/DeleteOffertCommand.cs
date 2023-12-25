using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Delete
{
    public record DeleteOffertCommand(int Id, int UserId):IRequest<BaseResponse<string>>;
 
}
