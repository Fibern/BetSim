using Application.Abstractions;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Repositories
{
    public class OffertRepository : IOffertRepository
    {
        private DbMainContext _context;

        public OffertRepository(DbMainContext context)
        {
            _context = context;
        }

        public async Task<int> AddAsync(Offert entity)
        {
            await _context.Offert.AddAsync(entity);
            await _context.SaveChangesAsync();
            return entity.Id;
        }

        public async Task<IReadOnlyList<Offert>> GetAllAsync(DateTime? dateTime = null)
        {
            IReadOnlyList<Offert> offerts = null;
            if(dateTime == null)
            {
                offerts = await _context.Offert.AsNoTracking().Include(e => e.Odds).ToListAsync();
            }
            else
            {
                offerts = await _context.Offert.AsNoTracking().Include(e => e.Odds).Where(
                    e => e.DateTime > dateTime 
                    && e.DateTime < dateTime + TimeSpan.FromDays(1) 
                    && e.Active == true
                    ).ToListAsync();
            }

            return offerts;
        }

        public async Task<IReadOnlyList<Offert>> GetEventOffert(int eventId)
        {
            return await _context.Offert.AsNoTracking().Where(e => e.EventId == eventId).ToListAsync();
        }

        public async Task<Offert> GetUserOffert(int id, int userId)
        {
            return await _context.Offert.Where(e => e.Id == id).Include(e => e.Event).FirstOrDefaultAsync(e => e.Event.OwnerId == userId);
        }

        public async Task<bool> UpdateAsync(Offert entity)
        {
            _context.Offert.Update(entity);
            return (await _context.SaveChangesAsync() > 0);
        }
    }
}
