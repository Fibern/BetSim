using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.ViewModel
{
    internal class JwtViewModel
    {
        public required string AccessToken { get; set; }
        public required string RefreshToken { get; set; }
        public required string Email { get; set; }
    }
}
