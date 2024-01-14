
using System.ComponentModel.DataAnnotations;

namespace Application.Dto.UserDto
{
    public class UserScoreBoardDto
    {
        public int Place {get ; set;}
        public string Username { get; set; }
        public double Points { get; set; }

    }
}

