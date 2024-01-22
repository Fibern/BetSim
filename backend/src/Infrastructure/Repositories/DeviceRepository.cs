using Application.Abstractions;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Infrastructure.Repositories
{
    public class DeviceRepository : IDeviceRepository
    {
        private readonly DbMainContext _context;

        public DeviceRepository(DbMainContext context)
        {
            _context = context;
        }

        public async Task<int> AddAsync(int userId, string deviceToken)
        {
            DeviceToken device = new DeviceToken()
            {
                TokenId = deviceToken,
                UserId = userId
            };
            
            await _context.DeviceToken.AddAsync(device);
            await _context.SaveChangesAsync();

            return device.Id;
        }

        public void Delete(int userId, string deviceToken)
        {
            var device = _context.DeviceToken.FirstOrDefault();
            if(device is not null){
                _context.DeviceToken.Remove(device);
                _context.SaveChangesAsync();
            }
            _context.SaveChangesAsync();
        }

        public async Task<bool> IsUserDeviceAsync(int userId, string deviceToken)
        {
            bool result = await _context.DeviceToken
            .Where(e => e.UserId == userId && e.TokenId == deviceToken)
            .AnyAsync();
 
            return result;
        }
    }
}