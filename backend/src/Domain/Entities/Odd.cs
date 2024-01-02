namespace Domain.Entities
{
    public class Odd
    {
        public int Id { get; set; }
        public string PlayerName { get; set; }
        public double OddValue { get; set; }
        public int? OffertId { get; set; }
        public Offert? Offert { get; set; }
    }
}
