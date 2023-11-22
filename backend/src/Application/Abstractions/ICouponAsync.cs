using BetSimApi.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    internal interface ICouponAsync: IAsyncRepository<Coupon>
    {
        Task<IReadOnlyList<Bet>> GetBetsByIdAsync(int id);

        Task<IReadOnlyList<Bet>> GetActiveAsync(int id);
    }
}
