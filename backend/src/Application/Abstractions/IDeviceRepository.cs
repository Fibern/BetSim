namespace Application.Abstractions
{
    public interface IDeviceRepository 
    {
        void Delete(int userId, string deviceToken);
        Task<int> AddAsync(int userId, string deviceToken);
        Task<bool> IsUserDeviceAsync(int userId, string deviceToken);
    }
}