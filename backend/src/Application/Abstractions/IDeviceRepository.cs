namespace Application.Abstractions
{
    public interface IDeviceRepository 
    {
        Task<int> AddAsync(int userId, string deviceToken);
        Task<bool> IsUserDeviceAsync(int userId, string deviceToken);
    }
}