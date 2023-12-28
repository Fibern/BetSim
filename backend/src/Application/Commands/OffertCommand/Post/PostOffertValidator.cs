using Application.Dto;
using Domain.Enums;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Post
{
    public class PostOffertValidator : AbstractValidator<OffertDto>
    {
        public PostOffertValidator(Func<bool> validateOdds)
        {
            RuleFor(p => p.Title).NotEmpty()
                .MaximumLength(30)
                .MinimumLength(3);
            RuleFor(p => p.DateTime).GreaterThan(DateTime.Now);
            RuleFor(p => p.Type).IsInEnum();
            RuleFor(p => p).Must(p => validateOdds() == true)
                .WithMessage("All odds values schould be equal to 100%");
        }
    }
}
