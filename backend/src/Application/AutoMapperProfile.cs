using Application.Commands.EventCommand.Post;
using Application.Commands.OffertCommand.Post;
using Application.ViewModel;
using AutoMapper;
using Domain.Entities;

namespace Application
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<Event, EventViewModel>().ReverseMap();
            CreateMap<PostOffertCommand, Offert>();
        }
    }
}
