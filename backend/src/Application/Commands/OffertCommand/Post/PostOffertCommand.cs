using Application;
using Application.Dto;
using Application.Dto.ViewModel;
using Domain.Entities;
using Domain.Enums;
using MediatR;

namespace Application.Commands.OffertCommand.Post
{
    public record PostOffertCommand(OffertDto offertDto, int EventId) : IRequest<BaseResponse<int>>;

}
