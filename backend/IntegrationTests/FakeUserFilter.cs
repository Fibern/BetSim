//using Microsoft.AspNetCore.Mvc.Filters;
//using System.Security.Claims;

//namespace IntegrationTests
//{
//    public class FakeUserFilter : IAsyncActionFilter
//    {
//        public async Task OnActionExecutionAsync(ActionExecutingContext context, ActionExecutionDelegate next)
//        {
//            context.HttpContext.User = new System.Security.Claims.ClaimsPrincipal(new ClaimsIdentity(new List<Claim>
//            {
//                new Claim(ClaimTypes.Name,"User" ),
//                new Claim(ClaimTypes.Role,"BetMaker"),
//                new Claim(ClaimTypes.Email,"user@gmail.com")
//            }));
//            await next();
//        }
//    }
//}
