using Microsoft.Extensions.Configuration;
using Microsoft.EntityFrameworkCore;
using Application.Abstractions;
using Infrastructure.Repositories;
using Microsoft.Extensions.DependencyInjection;

namespace Infrastructure;
public static class DependencyInjection
{
    public static IServiceCollection AddInfraStucture(this IServiceCollection services, IConfiguration configuration)
    {
        services.AddDbContext<DbMainContext>(o => o.UseNpgsql(configuration.GetConnectionString("Dbconnect"),
        builder => builder.EnableRetryOnFailure()));

        //app services
        services.AddScoped<IEventRepository, EventRepository>();

        //services.AddScoped(typeof(IAsyncRepository<>), typeof(BaseRepository<>));

        //services.AddScoped<ICategoryRepository, CategoryRepository>();
        //services.AddScoped<IWebinaryRepository, WebinaryRepository>();
        //services.AddScoped<IPostRepository, PostRepository>();

        return services;
    }
}
