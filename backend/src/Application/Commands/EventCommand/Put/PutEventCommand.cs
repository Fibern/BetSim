using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Put
{
    public class PutEventCommand : IRequest<BaseResponse<int>>
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Icon { get; set; }
    }
}
