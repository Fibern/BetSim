using System.Text;
using Application.Dto.EventDto;
using Domain.Entities;
using FluentAssertions;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.Build.Execution;
using Newtonsoft.Json;

namespace IntegrationTests
{
    public class HttpClientTests : IClassFixture<IntegrationTestsWebApplicationFactory>
    {
   
        private readonly HttpClient _client;

        public HttpClientTests(IntegrationTestsWebApplicationFactory factory)
        {
            _client = factory.CreateClient();
        }

         [Theory]
        [InlineData("/Event")]
        [InlineData("/Offert")]
        public async Task GetAll_returnOk(string url)
        {
            //arrange

            var response = await _client.GetAsync(url);

            //assertion

            response.StatusCode.Should().Be(System.Net.HttpStatusCode.OK);

        }

        [Fact]
        public async Task ShouldBlockHttpOverride()
        {
            //arrange
            _client.DefaultRequestHeaders.Add("X-Method-Override", "GET");

            EventDto eventDto = new EventDto("NewEvent", "icon");
            HttpContent httpContent = new StringContent(JsonConvert.SerializeObject(eventDto));

            //act         
            var response = await _client.PostAsync("/Event",httpContent);

            //assertion
            response.StatusCode.Should().Be(System.Net.HttpStatusCode.Forbidden);
        }
    }
}
