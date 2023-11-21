using BetSimApi;
using Microsoft.Extensions.Configuration;
using Microsoft.EntityFrameworkCore;
using BetSimApi.Abstracions;
using Application.Abstractions;
using Infrastructure.Repositories;

namespace Microsoft.Extensions.DependencyInjection;
    public static class DependencyInjection
    {
        public static IServiceCollection AddInfraStucture(this IServiceCollection services, IConfiguration configuration)
        {
            services.AddDbContext<DbMainContext>(o => o.UseNpgsql(configuration.GetConnectionString("Dbconnect"),
            builder => builder.EnableRetryOnFailure()));

            //app services
            services.AddScoped<IConnectionFactory, PostgresConnectionFactory>();
            services.AddScoped<IEventRepository, EventRepository>();

            //services.AddScoped(typeof(IAsyncRepository<>), typeof(BaseRepository<>));

            //services.AddScoped<ICategoryRepository, CategoryRepository>();
            //services.AddScoped<IWebinaryRepository, WebinaryRepository>();
            //services.AddScoped<IPostRepository, PostRepository>();

            return services;
        }
    }
