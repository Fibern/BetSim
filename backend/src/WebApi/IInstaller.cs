namespace WebApi
{
        public interface IInstaller
        {
            void InstallServices(IServiceCollection services, IConfiguration Configuration);
        }
}
