using Application.Abstractions;
using AutoMapper;
using Domain.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands.OffertCommand.Post
{
    public class PostOffertHandler : IRequestHandler<PostOffertCommand, BaseResponse<int>>
    {
        private IMapper _mapper;
        private IOffertRepository _ofertRepo;

        public PostOffertHandler(IMapper mapper, IOffertRepository offert)
        {
            _mapper = mapper;
            _ofertRepo = offert;
        }

        public async Task<BaseResponse<int>> Handle(PostOffertCommand request, CancellationToken cancellationToken)
        {
            Offert newOffert = _mapper.Map<Offert>(request);
            var validator = new PostOffertValidator(newOffert.ValidateOdds);
            var validationResult = validator.Validate(request);

            //if not valid
            if (validationResult.IsValid == false) return new BaseResponse<int>(validationResult);

            int offertId = await _ofertRepo.AddAsync(newOffert);

            return new BaseResponse<int>(offertId);
        }
    }
}
