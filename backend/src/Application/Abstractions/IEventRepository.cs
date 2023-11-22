using BetSimApi.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IEventRepository:IAsyncRepository<Event>
    {
        Task<IEnumerable<Event>> GetActiveAsync();
    }
}
