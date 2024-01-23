using Application.Dto.OffertDto;
using Domain.Entities;
using Domain.Enums;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Post
{
    public class PostOffertValidator : AbstractValidator<Offert>
    {
        public PostOffertValidator(Func<bool> validateOdds)
        {
            RuleFor(p => p.Title).NotEmpty();
            RuleFor(p => p.DateTime).GreaterThan(DateTimeOffset.Now);
            RuleFor(p => p.Type).IsInEnum();
            RuleFor(p => p.Odds).Must(p => p.Count() > 1)
                .WithMessage("Offert must have at least two odds");
            RuleFor(p => p.Odds).Must(p => validateOdds() == true)
                .WithMessage("All odds values schould be equal to 100%");
        }
    }
}
