using AutoMapper;
using BetSimApi.Commands;
using BetSimApi.Model;
using BetSimApi.ViewModel;

namespace BetSimApi
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        { 
            CreateMap<Event, EventViewModel>().ReverseMap();
            CreateMap<PostEventCommand, Event>();
        }
    }
}
