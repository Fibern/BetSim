using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand.Post
{
    public class PostEventValidator : AbstractValidator<PostEventCommand>
    {
        public PostEventValidator()
        {
            RuleFor(p => p.Title)
                    .NotEmpty()
                    .WithMessage("{PropertyName} is required")
                    .MaximumLength(80)
                    .WithMessage("{PropertyName} must not exceed 80 characters");
        }


    }
}
