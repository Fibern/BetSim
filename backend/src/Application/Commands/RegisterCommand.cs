using Application.ViewModel;
using MediatR;

namespace Application.Commands
{
    public class RegisterComannd : IRequest<JwtViewModel>
    {
        public required string UserName { get; set; }
        public required string Password { get; set; }
        public required string Email { get; set; }
    }
}
