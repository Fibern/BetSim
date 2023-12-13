using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.AspNetCore.TestHost;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Microsoft.EntityFrameworkCore;
using Infrastructure;
using Microsoft.AspNetCore.Authorization.Policy;
using FluentAssertions.Common;

namespace IntegrationTests
{
    public class IntegrationTestsWebApplicationFactory : WebApplicationFactory<Program>
    {
        protected override void ConfigureWebHost(IWebHostBuilder builder)
        {
            builder.ConfigureTestServices(s =>
            {
                s.AddSingleton<IPolicyEvaluator, FakePolicyEvaluator>();
                base.ConfigureWebHost(builder);
                s.AddDbContext<DbMainContext>(options =>
                {
                    // This is what makes a unique in-memory database per instance of TestWebApplicationFactory
                    options.UseInMemoryDatabase("database");
                });


            });

        }
    }
}
