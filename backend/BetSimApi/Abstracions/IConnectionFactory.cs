using Npgsql;

namespace BetSimApi.Abstracions
{
    public interface IPostgresConnectionFactory
    {
        NpgsqlConnection CreateConnection();
    }
}
