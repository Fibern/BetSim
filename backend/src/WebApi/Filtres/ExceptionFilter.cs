using Application;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace WebApi.Filtres
{
    public class ExceptionFilter : IExceptionFilter
    {

        public void OnException(ExceptionContext context)
        {
            var response = new BaseResponse<string>(context.Exception.Message,false);

            context.Result = new JsonResult(response) { StatusCode = 500};
        }
    }
}
