using FluentAssertions;
using Microsoft.AspNetCore.Mvc.Testing;

namespace IntegrationTests
{
    public class EventControllerTests
    {
        //arrange
        [Fact]
        public async Task GetAll_returnOk()
        {
            //arrange

            var factory = new WebApplicationFactory<Program>();

            var client = factory.CreateClient();

            //act 

            var response = await client.GetAsync("/Event");

            response.StatusCode.Should().Be(System.Net.HttpStatusCode.OK);

        }
    }
}
