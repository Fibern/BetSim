using FluentValidation.Results;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application
{
    public class BaseResponse<T>
    {
        public bool Succes { get; set; }
        public T Message { get; set; }
        public Dictionary<string, string> Errors { get; set; } = new Dictionary<string, string>();

        public BaseResponse()
        {
            Succes = true;
        }

        public BaseResponse(bool succes)
        {
            Succes = succes;
        }

        public BaseResponse(T message, bool succes)
        {
            Succes = succes;
            Message = message;
        }

        public BaseResponse(ValidationResult result)
        {
            Errors = result.Errors.ToDictionary(t => t.PropertyName, t => t.ErrorMessage);

            Succes = result.Errors.Count() < 0;
        }

        public BaseResponse(string ErrorMesage)
        {
            Errors.Add("ErrorMessage", ErrorMesage);

            Succes = false;
        }
    }
}
