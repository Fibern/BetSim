using Npgsql;

namespace BetSimApi.Abstracions
{
    public interface IConnectionFactory
    {
        NpgsqlConnection CreateConnection();
    }
}
