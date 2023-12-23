using Application.ViewModel;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.EventQuery
{
    public record GetMyEventsQuery(int UserId) : IRequest<BaseResponse<IReadOnlyList<EventViewModel>>>;

}
