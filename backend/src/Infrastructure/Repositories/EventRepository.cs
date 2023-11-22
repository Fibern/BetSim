using Application.Abstractions;
using BetSimApi;
using BetSimApi.Model;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory.Database;

namespace Infrastructure.Repositories
{
    internal class EventRepository : IEventRepository
    {
        private DbMainContext _context;
        public EventRepository(DbMainContext context)
        {
            _context = context;
        }

        public async Task<int> AddAsync(Event entity)
        {
            await _context.Event.AddAsync(entity);
            await _context.SaveChangesAsync();

            return entity.Id;
        }

        public Task<IEnumerable<Event>> GetActiveAsync()
        {
            throw new NotImplementedException();
        }

        public async Task<IReadOnlyList<Event>> GetAllAsync()
        {
            return _context.Event.AsNoTracking().ToImmutableList();
        }

        public Task<IReadOnlyList<Event>> GetAllMyAsync()
        {
            throw new NotImplementedException();
        }

        public Task UpdateAsync(Event entity)
        {
            throw new NotImplementedException();
        }
    }
}
