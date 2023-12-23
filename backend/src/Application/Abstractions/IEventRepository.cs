﻿using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Abstractions
{
    public interface IEventRepository:IAsyncRepository<Event>
    {
        Task<Event> GetById(int Id);
        Task<IReadOnlyList<Event>> GetAllAsync(bool active = true);

        Task<IReadOnlyList<Event>> GetAllMyAsync(int UserId);

        Task<bool> UpdateAsync(Event entity);

        Task<bool> ArchiveAsync(int eventId);
    }
}
