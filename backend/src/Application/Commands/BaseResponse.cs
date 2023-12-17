using FluentValidation.Results;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.Commands
{
    public class BaseResponse
    {
        public bool Succes { get; set; }
        public string Message{ get; set; }
        public Dictionary<string, string> ValidationErrors { get; set; } = new Dictionary<string, string>();

        public BaseResponse()
        {
            Succes = true;
        }

        public BaseResponse(string message, bool succes)
        {
            Succes = succes;
            Message = message;
        }

        public BaseResponse(ValidationResult result)
        {
            ValidationErrors = result.Errors.ToDictionary(t => t.PropertyName, t => t.ErrorMessage);

            Succes = result.Errors.Count() < 0;
        }
    }
}
