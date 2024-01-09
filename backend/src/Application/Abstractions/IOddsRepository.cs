
using Domain.Entities;

namespace Application.Abstractions
{
    public interface IOddsRepository
    {
        Task<IReadOnlyList<Odd>> GetOddsByListOfIdAsync(ICollection<int> Ids);
    }
}