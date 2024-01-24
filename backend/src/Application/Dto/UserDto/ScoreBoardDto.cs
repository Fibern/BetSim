
using System.ComponentModel.DataAnnotations;

namespace Application.Dto.UserDto
{
    public class ScoreBoardDto
    {
        public List<UserScoreBoardDto> TopUsers { get; set; }
        public UserScoreBoardDto User { get; set; }
    }
}

