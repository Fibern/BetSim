using Application.Dto.OffertDto;
using MediatR;

namespace Application.Queries.OffertQuery
{
    public record GetOffertByEventQuery(int EventId) : IRequest<BaseResponse<IReadOnlyList<GetOffertDto>>>;
}