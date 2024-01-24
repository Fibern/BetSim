
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace Application.Dto.UserDto
{
    public class UserScoreBoardDto
    {
        [JsonIgnore]
        public int Id { get; set; }
        public int Place {get ; set;}
        public string UserName { get; set; }
        public double Points { get; set; }

    }
}

