using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IAsyncRepository<T> where T : class
    {

        Task<IReadOnlyList<T>> GetAllAsync();

        Task<IReadOnlyList<T>> GetAllMyAsync();

        Task<int> AddAsync(T entity);

        Task UpdateAsync(T entity);
    }
}
