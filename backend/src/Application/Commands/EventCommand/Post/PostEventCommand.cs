using Application.Dto;
using Domain.Entities;
using MediatR;
using System.ComponentModel.DataAnnotations;

namespace Application.Commands.EventCommand.Post
{
    public record PostEventCommand(EventDto eventDto, int OwnerId) : IRequest<BaseResponse<int>>;
}
