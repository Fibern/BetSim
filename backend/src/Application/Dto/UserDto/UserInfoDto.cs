
using System.ComponentModel.DataAnnotations;

namespace Application.Dto.UserDto{
    public class UserInfoDto
    {
        public string UserName { get; set; }
        public string Email { get; set; }
        public double Points { get; set; }
        public bool IsOddsMaker { get; set; }
    }
}

