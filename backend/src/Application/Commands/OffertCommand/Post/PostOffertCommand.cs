using Application;
using Domain.Entities;
using Domain.Enums;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Post
{
    public record PostOffertCommand(string Title, BetType Type, DateTime Date, List<Odd> Odds, int EventId) : IRequest<BaseResponse<int>>;

}
