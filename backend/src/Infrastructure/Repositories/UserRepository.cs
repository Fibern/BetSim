using Application.Abstractions;
using Application.Dto.UserDto;
using Domain.Entities;
using Domain.UseCase;
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

        public async Task<int> AddAsync(User user)
        {
            await _context.Users.AddAsync(user);
            return user.Id;
        }

        public void Delete(int userId)
        {
            var user = _context.User.FirstOrDefault(e => e.Id == userId);
            if(user is not null){
                _context.User.Remove(user);
                _context.SaveChangesAsync();
            }
            
        }

        public async Task<User> GetUserInfoAsync(int userId)
        {
            User user = await _context.User
            .FirstOrDefaultAsync(e => e.Id == userId);
            
            return user;
        }

        public async Task<List<User>> GetUserScoreSortedAsync()
        {

            var AdminsId = _context.UserRoles
            .AsNoTracking()
            .Where(e => e.RoleId == 1)
            .Select(e=> e.UserId);

            var users = _context.Users
            .AsNoTracking()
            .OrderByDescending(e => e.Points)
            .Where(e =>  !AdminsId.Contains(e.Id))
            .ToList();
                                       

            return users;
        }

        public async Task<bool> PutAsync(User user)
        {
            _context.User.Update(user);
            return (await _context.SaveChangesAsync() > 0);
        }

    }
}