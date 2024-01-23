using System.Collections.Immutable;
using Application.Abstractions;
using Application.Dto.CouponDto;
using Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Infrastructure.Repositories
{
    public class CouponRepository : ICouponRepository
    {
        private DbMainContext _context;

        public CouponRepository(DbMainContext context)
        {  
            _context = context;
        }

        public async Task<int> AddAsync(Coupon coupon)
        {
            await _context.Coupon.AddAsync(coupon);
            await _context.SaveChangesAsync();

            return coupon.Id;
        }

        public async Task<IReadOnlyList<Coupon>> GetAsync(int userId)
        {
            IReadOnlyList<Coupon> allCoupons =  await _context.Coupon
            .AsNoTracking()
            .Include(e=> e.Bets)
            .ThenInclude(e => e.PredictedWinner)
            .Where(e => e.UserId == userId)
            .ToListAsync();
            return allCoupons;
        }

        public async Task<List<Coupon>> GetCouponsByOffert(int offertId)
        {
            List<Coupon> allCoupons =  await _context.Coupon
            .Include(e=> e.User)
            .Include(e=> e.Bets)
            .Where(e => e.Bets.Where(e => e.OffertId == offertId).Any())
            .ToListAsync();
            
            return allCoupons;
        }

        public async Task<bool> UpdateRangeAsync(List<Coupon> coupon)
        {
            _context.Coupon.UpdateRange(coupon);
            return (await _context.SaveChangesAsync() > 0);
        }
    }
}