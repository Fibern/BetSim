using Application.Dto.CouponDto;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface ICouponRepository
    {
        Task<int> AddAsync(Coupon coupon);
        Task<IReadOnlyList<Coupon>> GetAsync(int userId);
        Task<List<Coupon>> GetCouponsByOffert(int offertId);
        Task<bool> UpdateRangeAsync(List<Coupon> coupon);
    }
}
