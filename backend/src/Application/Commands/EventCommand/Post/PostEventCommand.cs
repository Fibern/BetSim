using Domain.Entities;
using MediatR;
using System.ComponentModel.DataAnnotations;

namespace Application.Commands.EventCommand.Post
{
    public record PostEventCommand : IRequest<BaseResponse<int>>
    {
        public string Title { get; set; }
        public string Icon { get; set; }
        public User Owner { get; set; }
    }
}
