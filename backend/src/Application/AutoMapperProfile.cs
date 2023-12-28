using Application.Commands.EventCommand.Post;
using Application.Commands.OffertCommand.Post;
using Application.Dto.EventDto;
using Application.Dto.OddDto;
using Application.Dto.OffertDto;
using AutoMapper;
using Domain.Entities;

namespace Application
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<Event, GetEventDto>().ReverseMap();
            CreateMap<PostOddDto, Odd>();
            CreateMap<OffertDto, Offert>().ReverseMap();
            CreateMap<Offert, GetOffertDto>();
            CreateMap<Odd, GetOddDto>();
        }
    }
}
