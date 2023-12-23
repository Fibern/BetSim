using Domain.Entities;
using MediatR;
using System.ComponentModel.DataAnnotations;

namespace Application.Commands.EventCommand.Post
{
    public record PostEventCommand(string Title, string Icon, int OwnerId) : IRequest<BaseResponse<int>>;
}
