using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTests
{
    public class EventTests
    {
        [Fact]
        public void EventConstructor_CreateOwnerWithId()
        { 
            var eventTmp = new Event("title","icon",1);

            Assert.Equal(eventTmp.Owner.Id, 1);
        }
    }
}
