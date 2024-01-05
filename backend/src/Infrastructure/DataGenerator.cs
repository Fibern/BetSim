using Bogus;
using Domain.Entities;
using Infrastructure;

namespace MyNamespace
{
    public class DataGenerator
    {
        public void SeedEvents(DbMainContext context){
            
        }

        public void SeedOfferts(DbMainContext context){
            var offertsGenerator = new Faker<Offert>()
            .RuleFor(a => a.DateTime, f => f.Date.Soon());
        }
    }
}
