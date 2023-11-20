using BetSimApi.Model.Classes;
using MediatR;

namespace BetSimApi.Commands
{
    public class LoginCommand:IRequest<JwtInfo>
    {
        public required string UserName { get; set; }
        public required string Password { get; set; }
    }
}
