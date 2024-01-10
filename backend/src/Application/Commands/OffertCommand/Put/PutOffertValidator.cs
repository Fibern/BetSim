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
            RuleFor(p => p.Date).GreaterThan(DateTimeOffset.Now);
        }
  
    }
}
