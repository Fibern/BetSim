using System.ComponentModel.DataAnnotations;

namespace BetSimApi.Model.Classes
{
    public class JwtInfo
    {
        public required string AccessToken { get; set; }
        public required string RefreshToken { get; set; }
    }
}
