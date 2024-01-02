using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Patch
{
    public record PatchOffertScoreCommand(int Id, int UserId, int Winner ,string Score ):IRequest<BaseResponse<string>>;
}
