using Application;
using Application.Commands.EventCommand.Delete;
using Application.Commands.EventCommand.Post;
using Application.Dto.EventDto;
using Domain.Entities;
using Infrastructure;
using MediatR;
using Microsoft.AspNetCore.Identity;

namespace IntegrationTests;

public class EventTest : BaseIntegrationTestClass
{
    public EventTest(IntegrationTestsWebApplicationFactory factory) : base(factory)
    {
        var user = new User
        {
            UserName = "Test",
            Email = "test@example"
        };
        
        UserManager.CreateAsync(user).Wait();      
    }

    [Fact]
    public async void PostEventCommand_ShouldCreateEvent()
    {
        //arrange
        var dto = new EventDto("title", "icon");
        var command = new PostEventCommand(dto,1);

        //act
        BaseResponse<int> result = await Sender.Send(command);

        //assert
        var objResponse = DbMainContext.Event.FirstOrDefault(x => x.Id == result.Message);

        Assert.True(result.Succes);
        Assert.NotNull(objResponse);
        Assert.Equal("title", objResponse.Title);       
   }


    [Fact]
    public async void DeleteEventCommand_ShouldArchiveEvent()
    {
        //arrange
        var obj = new Event(){
            Title = "title2",
            Icon = "icon2",
            OwnerId = 1
        };

        DbMainContext.Event.Add(obj);
        DbMainContext.SaveChanges();

        DeleteEventCommand command = new DeleteEventCommand(obj.Id, 1);

        //act
        BaseResponse<string> result = await Sender.Send(command);

        //assert
        var objResponse = DbMainContext.Event.FirstOrDefault(x => x.Id == obj.Id);

        Assert.True(result.Succes);
        Assert.NotNull(objResponse);
        Assert.Equal("title2", obj.Title);
        Assert.False(objResponse.Active);              
   }


    [Fact]
    public async void DeleteEventCommand_WhenOffertIsActive_ShouldReturnSucessFalse()
    {
        //arrange
        var obj = new Event(){
            Title = "title3",
            Icon = "icon3",
            OwnerId = 1,
            Offerts = new List<Offert>{
                new Offert(){
                    Title = "offertTitle"
                }
            }
        };

        DbMainContext.Event.Add(obj);
        DbMainContext.SaveChanges();

        DeleteEventCommand command = new DeleteEventCommand(obj.Id, 1);

        //act
        BaseResponse<string> result = await Sender.Send(command);

        //assert
        Assert.False(result.Succes);          
   }
}
