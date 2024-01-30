using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.AspNetCore.TestHost;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Microsoft.EntityFrameworkCore;
using Infrastructure;
using Microsoft.AspNetCore.Authorization.Policy;
using FluentAssertions.Common;
using Testcontainers.PostgreSql;
using FluentAssertions;

namespace IntegrationTests
{
    public class IntegrationTestsWebApplicationFactory : WebApplicationFactory<Program>, IAsyncLifetime
    {
        private readonly PostgreSqlContainer _dbContainer = new PostgreSqlBuilder()
        .WithImage("postgres:latest")
        .WithDatabase("betSim")
        .WithUsername("postgres")
        .WithPassword("postgres")
        .WithCleanUp(true)        
        .Build();

        protected override void ConfigureWebHost(IWebHostBuilder builder)
        {
            builder.ConfigureTestServices(s =>
            {
                s.AddSingleton<IPolicyEvaluator, FakePolicyEvaluator>();
                base.ConfigureWebHost(builder);
                s.AddDbContext<DbMainContext>(options =>
                {
                    options.UseNpgsql(_dbContainer.GetConnectionString());
                });
            });
        }


        public Task InitializeAsync()
        {
            return _dbContainer.StartAsync();
        }

        Task IAsyncLifetime.DisposeAsync()
        {
            return _dbContainer.StopAsync();
        }

    }
}
