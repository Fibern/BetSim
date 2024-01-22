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
        var connection = configuration.GetConnectionString("Dbconnect");
        services.AddDbContext<DbMainContext>(o => o.UseNpgsql(configuration.GetConnectionString("Dbconnect"),
        builder => builder.EnableRetryOnFailure()));
        
        //app repository
        services.AddScoped<IDeviceRepository, DeviceRepository>();
        services.AddScoped<IEventRepository, EventRepository>();
        services.AddScoped<IOffertRepository, OffertRepository>();

        services.AddScoped<ICouponRepository, CouponRepository>();
        services.AddScoped<IOddsRepository , OddRepository>();
        services.AddScoped<IUserRepository, UserRepository>();

        return services;
    }
}
