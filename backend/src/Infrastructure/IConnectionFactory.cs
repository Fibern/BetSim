using Npgsql;

namespace Infrastructure
{
    public interface IConnectionFactory
    {
        NpgsqlConnection CreateConnection();
    }
}
