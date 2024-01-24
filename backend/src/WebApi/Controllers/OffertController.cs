using Application;
using Application.Commands.EventCommand.Put;
using Application.Commands.OffertCommand.Delete;
using Application.Commands.OffertCommand.Patch;
using Application.Commands.OffertCommand.Post;
using Application.Commands.OffertCommand.Put;
using Application.Dto.OffertDto;
using Application.Queries.OffertQuery;
using MediatR;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using System.Security.Claims;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class OffertController : ControllerBase
    {
        private IHttpContextAccessor _httpContextAccessor;
        private readonly IMediator _mediator;
        private int _userId;

        public OffertController(IMediator mediator, IHttpContextAccessor httpContextAccessor)
        {
            _mediator = mediator;
            var userId = httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier);
            if (userId != null) _userId = int.Parse(userId);
            _httpContextAccessor = httpContextAccessor;
        }

        [HttpGet]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetOffertDto>>>> OffertGet(DateTime? dateTime = null)
        {
            var command = new GetOffertQuery(dateTime);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpGet("{eventId}")]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetOffertDto>>>> OffertGetByEvent([FromRoute] int eventId)
        {
            var command = new GetOffertByEventQuery(eventId);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpPost("{eventId}")]
        [Authorize(Roles = "OddsMaker") ]
        public async Task<ActionResult<BaseResponse<int>>> OffertPost([FromRoute] int eventId,OffertDto offert)
        {
            var command = new PostOffertCommand(offert, eventId);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpPut("{id}")]
        [Authorize(Roles = "OddsMaker") ]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetOffertDto>>>> OffertPut([FromRoute] int id, PutOffertDto request)
        {
            var command = new PutOffertCommand(_userId, id, request);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        // [HttpDelete("{id}")]
        // [Authorize(Roles = "OddsMaker") ]
        // public async Task<ActionResult<BaseResponse<IReadOnlyList<GetOffertDto>>>> OffertDelete([FromRoute] int id)
        // {
        //     var command = new DeleteOffertCommand(id, _userId);
        //     var response = await _mediator.Send(command);

        //     return (response.Succes == true) ? Ok(response) : BadRequest(response);
        // }

        [HttpPatch("{offertId}")]
        [Authorize(Roles = "OddsMaker") ]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<GetOffertDto>>>> OffertPatch([FromRoute] int offertId, ScorePatchOffertDto request)
        {
            var command = new PatchOffertScoreCommand(offertId, _userId, request.winner, request.score);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }
    }
}
