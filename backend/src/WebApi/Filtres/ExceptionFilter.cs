﻿using Application;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Serilog;

namespace WebApi.Filtres
{
    public class ExceptionFilter : IExceptionFilter
    {
        private readonly Serilog.ILogger _logger;

        public ExceptionFilter(Serilog.ILogger logger)
        {
            _logger = logger;
        }

        public void OnException(ExceptionContext context)
        {
            _logger.Error(context.Exception, context.Exception.Message);

            var response = new BaseResponse<string>(context.Exception.Message,false);

            context.Result = new JsonResult(response) { StatusCode = 500};
        }
    }
}
