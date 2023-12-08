using FluentAssertions;
using Microsoft.AspNetCore.Mvc.Testing;

namespace IntegrationTests
{
    public class EventControllerTests : IClassFixture<WebApplicationFactory<Program>>
    {
   
        private readonly HttpClient _client;

        internal EventControllerTests(WebApplicationFactory<Program> factory)
        {
            _client = factory.CreateClient();
        }

        //arrange
        [Fact]
        public async Task GetAll_returnOk()
        {
            //act 

            var response = await _client.GetAsync("/Event");

            //assertion

            response.StatusCode.Should().Be(System.Net.HttpStatusCode.OK);

        }
    }
}
