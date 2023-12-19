using Application.ViewModel;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.EventQuery
{
    public class GetMyEventsQuery: IRequest<BaseResponse<IReadOnlyCollection<EventViewModel>>>
    {
        public int UserId { get; set; }
    }
}
