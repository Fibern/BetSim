using BetSimApi.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    internal interface IOffertRepository:IAsyncRepository<Offert>
    {
        Task<Offert> GetByIdAsync(int id);
        Task<IReadOnlyList<Offert>> GetActiveByIdAsync(int id);
        Task<IReadOnlyList<Offert>> GetTodayAsync(int id);
    }
}
