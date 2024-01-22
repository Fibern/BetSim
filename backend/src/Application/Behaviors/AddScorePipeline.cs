using Application.Commands.OffertCommand.Patch;
using MediatR;

namespace Application.Behaviors
{
    public class AddScorePipeline : IPipelineBehavior<PatchOffertScoreCommand, BaseResponse<string>>
    {
        public async Task<BaseResponse<string>> Handle(PatchOffertScoreCommand request, RequestHandlerDelegate<BaseResponse<string>> next, CancellationToken cancellationToken)
        {
            BaseResponse<string> response = await next(); 

            if (!response.Succes)return response;

            

            return response;
        }
    }
}
