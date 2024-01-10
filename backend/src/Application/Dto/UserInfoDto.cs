
using System.ComponentModel.DataAnnotations;

namespace Application.Dto.OffertDto{
    public class UserInfoDto
    {
        public string Email { get; set; }
        public double Points { get; set; }
        public bool IsOddsMaker { get; set; }
    }
}

