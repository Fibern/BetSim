namespace Domain.Entities
{
    public class DeviceToken
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public User User { get; set; }
        public string TokenId { get; set; }
    }
}