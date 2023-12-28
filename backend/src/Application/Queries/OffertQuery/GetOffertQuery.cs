using Application.Dto.ViewModel;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.OffertQuery
{
    public record GetOffertQuery(DateTime? dateTime = null) : IRequest<BaseResponse<IReadOnlyList<OffertViewModel>>>;
}
