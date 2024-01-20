using Application.Dto.OffertDto;
using Application.Dto.UserDto;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IUserRepository 
    {
        Task<int> AddAsync(User user);
        Task<bool> PutAsync(User user);
        Task<User> GetUserInfoAsync(int userId);
        Task<List<User>> GetUserScoreSortedAsync();
        void Delete(int userId);
    }
}
