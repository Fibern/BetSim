using Application.Abstractions;
using Application.Dto.UserDto;
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

        public async Task<int> AddAsync(User user)
        {
            await _context.Users.AddAsync(user);
            return user.Id;
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
            .FirstOrDefaultAsync(e => e.Id == userId);
            
            return user;
        }

        public async Task<List<User>> GetUserScoreSortedAsync()
        {
            // var users = await _context.User.AsNoTracking()
            // .OrderByDescending(e => e.Points)
            // .Select(e => new User{Id = e.Id, UserName = e.UserName, Points = e.Points})
            // .ToListAsync();

            // _context.UserRoles.ToList();


            var users = await (from role in _context.UserRoles.AsNoTracking()
                        join user in _context.User.AsNoTracking() on role.UserId equals user.Id into Users
                        from userNotAdmin in Users
                        orderby userNotAdmin.Points descending  
                        select new User{Id = userNotAdmin.Id, UserName = userNotAdmin.UserName, Points = userNotAdmin.Points}
                        ).ToListAsync();
                                       

            return users;
        }

        public async Task<bool> PutAsync(User user)
        {
            _context.User.Update(user);
            return (await _context.SaveChangesAsync() > 0);
        }

    }
}