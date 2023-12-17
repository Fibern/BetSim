using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authorization.Policy;
using System.Security.Claims;

namespace IntegrationTests
{
    public class FakePolicyEvaluator : IPolicyEvaluator
    {
        public async Task<AuthenticateResult> AuthenticateAsync(AuthorizationPolicy policy, HttpContext context)
        {
            var claimsPrincipal = new ClaimsPrincipal();

            claimsPrincipal.AddIdentity(new ClaimsIdentity(new[]
            {
                new Claim(ClaimTypes.Name,"User" ),
                new Claim(ClaimTypes.Role,"Oddsmaker"),
                new Claim(ClaimTypes.Email,"user@gmail.com")
            }, "FakeScheme"));

            return await Task.FromResult(AuthenticateResult.Success(
                new AuthenticationTicket(claimsPrincipal, new AuthenticationProperties(), "FakeScheme")));
        }

        public Task<PolicyAuthorizationResult> AuthorizeAsync(AuthorizationPolicy policy, AuthenticateResult authenticationResult, HttpContext context, object? resource)
        {
            var result = PolicyAuthorizationResult.Success();
            return Task.FromResult(result);
        }
    }
}
