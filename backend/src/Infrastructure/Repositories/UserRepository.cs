using Application.Abstractions;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Infrastructure.Repositories
{
    public class UserRepository : IUserRepository
    {
        private DbMainContext _context;

        public UserRepository(DbMainContext context)
        {
            _context = context;
        }

        public void Delete(int userId)
        {
            var user = _context.User.Find(userId);
            if(user is not null){
                _context.User.Remove(user);
                _context.SaveChangesAsync();
            }
            
        }

        public async Task<User> GetUserInfoAsync(int userId)
        {
            User user = await _context.User
            .AsNoTracking()
            .FirstOrDefaultAsync(e => e.Id == userId);
            
            return user;
        }
    }
}