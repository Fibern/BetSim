using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.AspNetCore.TestHost;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Microsoft.EntityFrameworkCore;
using Infrastructure;

namespace IntegrationTests
{
    internal class IntegrationTestsWebApplicationFactory : WebApplicationFactory<Program>
    {
        protected override void ConfigureWebHost(IWebHostBuilder builder)
        {
            builder.ConfigureTestServices(s =>
            {
                s.RemoveAll(typeof(DbContextOptions<DbMainContext>));
                var connectionString = GetConnectionString();
                s.AddDbContext<DbMainContext>(o => o.UseNpgsql(connectionString,
                    builder => builder.EnableRetryOnFailure()));

                
            });

        }

        private static string? GetConnectionString()
        {
            var configuration = new ConfigurationBuilder()
                .AddUserSecrets<IntegrationTestsWebApplicationFactory>()
                .Build();

            return configuration.GetConnectionString("Dbconnect");
        }
    }
}
