using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Put
{
    public class PutEventValidator : AbstractValidator<PutEventCommand>
    {
        public PutEventValidator()
        {
            RuleFor(p => p.Title)
            .NotEmpty()
            .WithMessage("{PropertyName} is required")
            .MaximumLength(80)
            .WithMessage("{PropertyName} must not exceed 80 characters");
            RuleFor(p => p.Id).NotNull().NotEmpty();
        }
    }
}
