using Application.Abstractions;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
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

        public async Task<IReadOnlyList<Event>> GetAllAsync(bool active = true)
        {
            return _context.Event.AsNoTracking().Where(o => o.Active == active).ToImmutableList();
        }


        public async Task<IReadOnlyList<Event>> GetAllMyAsync(int UserId)
        {
            return _context.Event.AsNoTracking().Where(o => o.Owner.Id == UserId).ToImmutableList();
        }

        public async Task<Event> GetById(int Id)
        {
            return _context.Event.FirstOrDefault(o => o.Id == Id);
        }

        public async Task<bool> UpdateAsync(Event entity)
        {
            _context.Event.Update(entity);
            return (await _context.SaveChangesAsync() > 0);
        }



    }
}
