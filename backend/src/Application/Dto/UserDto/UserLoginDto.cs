using System.ComponentModel.DataAnnotations;

namespace Application.Dto.UserDto{
    public class UserLoginDto
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }
}
