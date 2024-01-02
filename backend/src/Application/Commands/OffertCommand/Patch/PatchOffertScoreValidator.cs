using FluentValidation;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Patch
{
    public class PatchOffertScoreValidator: AbstractValidator<PatchOffertScoreCommand>
    {
        public PatchOffertScoreValidator(Func<bool> validateWinner)
        {
            RuleFor(e => e.Winner)
                .NotEmpty()
                .Must(e => validateWinner() == true)
                .WithMessage("Winner is out of scope");
            RuleFor(e => e.Score).NotEmpty();
        }
    }
}
