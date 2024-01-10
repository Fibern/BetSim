using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IOffertRepository:IAsyncRepository<Offert>
    {
        Task<Offert> GetUserOffert(int id, int userId);
        Task<IReadOnlyList<Offert>> GetEventOffertAsync(int eventId);
        Task<IReadOnlyList<Offert>> GetAllAsync(DateTimeOffset? dateTime = null);
    }
}
