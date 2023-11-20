using MediatR;
using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Commands
{
    public record PostEventCommand:IRequest<int>
    {
        public string Title { get; set; }
        public string Icon { get; set; }
    }
}
