﻿using Application.Dto.OffertDto;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Put
{
    public record PutOffertCommand(int UserId, int Id, PutOffertDto OfertDto) : IRequest<BaseResponse<int>>;

}
