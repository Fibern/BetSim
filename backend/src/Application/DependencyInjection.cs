using Application.Behaviors;
using Application.Commands.OffertCommand.Patch;
using MediatR;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Serilog;
using System.Reflection;


namespace Application;
public static class DependencyInjection
{
    public static IServiceCollection AddApplication(this IServiceCollection services,IConfiguration configuration)
    {
        services.AddTransient
            (
            typeof(IPipelineBehavior<,>), 
            typeof(AddScorePipeline<,>)
            );
        services.AddAutoMapper(Assembly.GetExecutingAssembly());
        services.AddMediatR(cfg => cfg.RegisterServicesFromAssembly(Assembly.GetExecutingAssembly()));

        Log.Logger = new LoggerConfiguration()
            .MinimumLevel.Information()
            .WriteTo.Console()
            .WriteTo.File(configuration["Logging:File"], rollingInterval: RollingInterval.Day)
            .CreateLogger();

        return services;
    }
}
