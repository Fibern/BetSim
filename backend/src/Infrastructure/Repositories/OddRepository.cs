using Application.Abstractions;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Infrastructure.Repositories
{
    public class OddRepository : IOddsRepository
    {
        private DbMainContext _context;

        public OddRepository(DbMainContext context)
        {
            _context = context;
        }

        public async Task<IReadOnlyList<Odd>> GetOddsByListOfIdAsync(ICollection<int> Ids)
        {
            return await _context.Odd.AsNoTracking().Where(e => Ids.Contains(e.Id)).ToListAsync();
        }
    }
}