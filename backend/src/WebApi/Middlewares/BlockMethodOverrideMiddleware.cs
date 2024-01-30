using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using System.Threading.Tasks;

public class BlockMethodOverrideMiddleware
{
    private readonly RequestDelegate _next;

    public BlockMethodOverrideMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        if (context.Request.Headers.ContainsKey("X-HTTP-Method-Override") ||
            context.Request.Headers.ContainsKey("X-HTTP-Method") ||
            context.Request.Headers.ContainsKey("X-Method-Override"))
        {
            context.Response.StatusCode = 403; // Forbidden
            return;
        }

        await _next(context);
    }
}

public static class BlockMethodOverrideMiddlewarextensions
{
    public static IApplicationBuilder UseBlockMethodOverrideMiddleware(this IApplicationBuilder builder)
    {
        return builder.UseMiddleware<BlockMethodOverrideMiddleware>();
    }
}