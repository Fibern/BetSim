using Application;
using Application.Commands.EventCommand.Put;
using Application.Commands.OffertCommand.Delete;
using Application.Commands.OffertCommand.Patch;
using Application.Commands.OffertCommand.Post;
using Application.Commands.OffertCommand.Put;
using Application.Dto;
using Application.Dto.ViewModel;
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
        public async Task<ActionResult<BaseResponse<IReadOnlyList<OffertViewModel>>>> OffertGet(DateTime? dateTime = null)
        {
            var command = new GetOffertQuery(dateTime);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpPost("{eventId}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<int>>> OffertPost([FromRoute] int eventId,OffertDto offert)
        {
            var command = new PostOffertCommand(offert, eventId);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpPut("{id}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<OffertViewModel>>>> OffertPut([FromRoute] int offertId, string title, DateTime datetime)
        {
            var command = new PutOffertCommand(_userId, offertId,title, datetime);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpDelete("{id}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<OffertViewModel>>>> OffertDelete([FromRoute] int offertId)
        {
            var command = new DeleteOffertCommand(offertId, _userId);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }

        [HttpPatch("{id}")]
        [Authorize]
        public async Task<ActionResult<BaseResponse<IReadOnlyList<OffertViewModel>>>> OffertPatch([FromRoute] int offertId,int winner,string score)
        {
            var command = new PatchOffertScoreCommand(offertId, _userId, winner, score);
            var response = await _mediator.Send(command);

            return (response.Succes == true) ? Ok(response) : BadRequest(response);
        }
    }
}
