using Application.Commands.EventCommand.Post;
using Application.Commands.OffertCommand.Post;
using Application.Dto;
using Application.Dto.ViewModel;
using AutoMapper;
using Domain.Entities;

namespace Application
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<Event, EventViewModel>().ReverseMap();
            CreateMap<PostOddDto, Odd>();
            CreateMap<OffertDto, Offert>().ReverseMap();
            CreateMap<Offert, OffertViewModel>();
            CreateMap<Odd, OddViewModel>();
        }
    }
}
