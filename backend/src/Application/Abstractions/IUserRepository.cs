using Application.Dto.OffertDto;
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
        Task<User> GetUserInfoAsync(int userId);
        void Delete(int userId);
    }
}
