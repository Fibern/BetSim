using BetSimApi.Abstracions;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace BetSimApi
{
    public class PostgresConnectionFactory : IConnectionFactory
    {
        private IConfiguration _configuration;

        public PostgresConnectionFactory(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public NpgsqlConnection CreateConnection()
        {
            return new NpgsqlConnection(_configuration.GetConnectionString("Dbconnect"));
        }
    }
}
