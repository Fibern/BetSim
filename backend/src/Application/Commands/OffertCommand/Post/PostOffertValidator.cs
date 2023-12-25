using Domain.Enums;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Post
{
    public class PostOffertValidator : AbstractValidator<PostOffertCommand>
    {
        public PostOffertValidator(Func<bool> validateOdds)
        {
            RuleFor(p => p.Title).NotEmpty()
                .MaximumLength(30)
                .MinimumLength(3);
            RuleFor(p => p.Date).GreaterThan(DateTime.Now);
            RuleFor(p => p.Type).IsInEnum();
            RuleFor(p => p).Must(p => validateOdds() == true)
                .WithMessage("All courses schould be equal to 100%");
        }
    }
}
