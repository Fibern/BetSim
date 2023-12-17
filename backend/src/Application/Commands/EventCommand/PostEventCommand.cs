using MediatR;
using System.ComponentModel.DataAnnotations;

namespace Application.Commands.EventCommand
{
    public record PostEventCommand : IRequest<int>
    {
        public string Title { get; set; }
        public string Icon { get; set; }
    }
}
