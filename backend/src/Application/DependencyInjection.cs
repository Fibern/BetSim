using Application.Behaviors;
using Application.Commands.OffertCommand.Patch;
using MediatR;
using Microsoft.Extensions.DependencyInjection;
using System.Reflection;


namespace Application;
public static class DependencyInjection
{
    public static IServiceCollection AddApplication(this IServiceCollection services)
    {
        services.AddTransient<IPipelineBehavior<PatchOffertScoreCommand, BaseResponse<string>>, AddScorePipeline>();
        services.AddAutoMapper(Assembly.GetExecutingAssembly());
        services.AddMediatR(cfg => cfg.RegisterServicesFromAssembly(Assembly.GetExecutingAssembly()));

        return services;
    }
}
