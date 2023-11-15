using AutoMapper;
using BetSimApi.Model;
using BetSimApi.ViewModel;

namespace BetSimApi
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        { 
            CreateMap<Event, EventViewModel>().ReverseMap();
        }
    }
}
