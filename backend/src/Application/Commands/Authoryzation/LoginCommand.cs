using Application.ViewModel;
using MediatR;

namespace Application.Commands.Authoryzation
{
    public class LoginCommand : IRequest<JwtViewModel>
    {
        public required string UserName { get; set; }
        public required string Password { get; set; }
    }
}
