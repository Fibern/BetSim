using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Put
{
    public class PutOffertValidator: AbstractValidator<PutOffertCommand>
    {
        public PutOffertValidator(Func<bool> ValidateOdds)
        {
            RuleFor(p => p.Title).NotEmpty()
              .MaximumLength(30)
              .MinimumLength(3);
            RuleFor(p => p.Date).GreaterThan(DateTime.Now);
            RuleFor(p => p).Must(p => ValidateOdds() == true)
                    .WithMessage("All courses schould be equal to 100%");
        }
  
    }
}
