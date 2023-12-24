using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IAsyncRepository<T> where T : class
    {

        Task<int> AddAsync(T entity);

        Task<bool> UpdateAsync(T entity);

    }
}
