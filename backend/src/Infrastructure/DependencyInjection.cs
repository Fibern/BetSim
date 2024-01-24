using Microsoft.Extensions.Configuration;
using Microsoft.EntityFrameworkCore;
using Application.Abstractions;
using Infrastructure.Repositories;
using Microsoft.Extensions.DependencyInjection;
using FirebaseAdmin;
using Google.Apis.Auth.OAuth2;

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

        services.AddScoped<IPushNotificationService, PushNotyficationService>();

        FirebaseApp.Create(new AppOptions()
        {
            Credential = GoogleCredential.FromFile(configuration["Firebase:File"]),
        });                                                                                                 

        return services;
    }
}
