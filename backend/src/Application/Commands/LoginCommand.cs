﻿using Application.ViewModel;
using MediatR;

namespace BetSimApi.Commands
{
    public class LoginCommand:IRequest<JwtViewModel>
    {
        public required string UserName { get; set; }
        public required string Password { get; set; }
    }
}
