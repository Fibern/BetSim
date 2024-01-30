using Domain.Entities;
using Infrastructure;
using MediatR;
using Microsoft.AspNetCore.Identity;

namespace IntegrationTests;

public class BaseIntegrationTestClass : IClassFixture<IntegrationTestsWebApplicationFactory>
{
    private readonly IServiceScope _scope;

    protected readonly DbMainContext DbMainContext;
    protected readonly UserManager<User> UserManager;
    protected readonly ISender Sender;

    public BaseIntegrationTestClass(IntegrationTestsWebApplicationFactory factory) 
    {
        _scope = factory.Services.CreateScope(); 
        Sender = _scope.ServiceProvider.GetRequiredService<ISender>();
        DbMainContext = _scope.ServiceProvider.GetRequiredService<DbMainContext>();
    }
}
