﻿using Application.Dto.OffertDto;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Queries.OffertQuery
{
    public record GetOffertQuery(DateTimeOffset? dateTime = null) : IRequest<BaseResponse<IReadOnlyList<GetOffertDto>>>;
}
