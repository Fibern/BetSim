﻿using Domain.Entities;
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
        Task<IReadOnlyList<Offert>> GetEventOffert(int eventId);
        Task<IReadOnlyList<Offert>> GetAllAsync(DateTime? dateTime = null);
    }
}
