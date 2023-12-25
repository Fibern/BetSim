using Application.Dto;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.EventCommand
{
    public class EventValidatorDto : AbstractValidator<EventDto>
    {
        public EventValidatorDto()
        {
            RuleFor(p => p.Title)
                    .NotEmpty()
                    .MaximumLength(30)
                    .MinimumLength(3);
        }


    }
}
