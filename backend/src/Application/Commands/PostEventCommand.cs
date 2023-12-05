using MediatR;
using System.ComponentModel.DataAnnotations;

namespace Application.Commands
{
    public record PostEventCommand : IRequest<int>
    {
        public string Title { get; set; }
        public string Icon { get; set; }
    }
}
