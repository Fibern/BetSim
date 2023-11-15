using BetSimApi.Abstracions;
using Npgsql;

namespace BetSimApi
{
    public class PostgresConnection : IConnectionFactory
    {
        private IConfiguration _configuration;

        public PostgresConnection(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public NpgsqlConnection CreateConnection()
        {
            return new NpgsqlConnection(_configuration.GetConnectionString("Dbconnect"));
        }
    }
}
