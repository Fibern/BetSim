using Application;
using Domain.Entities;
using Infrastructure;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using System.Configuration;
using System.Text;
using WebApi.Filtres;

var builder = WebApplication.CreateBuilder(args);

var configuration = builder.Configuration;


// configure kerstel for https
// builder.WebHost.ConfigureKestrel( o => o.ListenLocalhost(7054));
if(builder.Environment.IsProduction()){
    builder.WebHost.UseUrls("https://sport-bet-simulator.pl");
}

builder.Services.AddApplication();
builder.Services.AddInfraStucture(builder.Configuration);

builder.Services.AddHttpContextAccessor();
builder.Services.AddControllers(
   cfg =>
{
    if(builder.Environment.IsProduction()){
        cfg.Filters.Add(typeof(ExceptionFilter));
    }
}
);
// cognit configuration
builder.Services.AddCognitoIdentity();
var userPoolId = builder.Configuration["AWS:UserPoolId"];

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
 .AddJwtBearer(options =>
    {
        options.Authority = $"https://cognito-idp.eu-central-1.amazonaws.com/{userPoolId}";
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateLifetime = true,
            ValidAudience = configuration["AWS:AppClientId"],
            ValidateAudience = true,
            RoleClaimType = "cognito:groups"
        };
    });

//configure identity
// builder.Services.AddIdentityApiEndpoints<User>()
//     .AddEntityFrameworkStores<DbMainContext>();


//configre authorization
builder.Services.AddAuthentication();

builder.Services.AddAuthorizationBuilder();

builder.Services.Configure<IdentityOptions>(options =>
{
    // Default Password settings.
    options.SignIn.RequireConfirmedAccount = false;
    options.User.RequireUniqueEmail = true;
    options.Password.RequireDigit = false;
    options.Password.RequireLowercase = false;
    options.Password.RequireNonAlphanumeric = false;
    options.Password.RequireUppercase = false;
    options.Password.RequiredLength = 6;
    options.Password.RequiredUniqueChars = 1;
});


// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();

//add swager with authorization
builder.Services.AddSwaggerGen(option =>
{
    option.SwaggerDoc("v1", new OpenApiInfo { Title = "Demo API", Version = "v1" });
    option.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        In = ParameterLocation.Header,
        Description = "Please enter a valid token",
        Name = "Authorization",
        Type = SecuritySchemeType.Http,
        BearerFormat = "JWT",
        Scheme = "Bearer"
    });
    option.AddSecurityRequirement(new OpenApiSecurityRequirement
    {
        {
            new OpenApiSecurityScheme
            {
                Reference = new OpenApiReference
                {
                    Type=ReferenceType.SecurityScheme,
                    Id="Bearer"
                }
            },
            new string[]{}
        }
    });
});

var app = builder.Build();

//add seciurity headers
//https://cheatsheetseries.owasp.org/cheatsheets/REST_Security_Cheat_Sheet.html

app.Use(async (context, next) =>
{
    context.Response.Headers.Append("X-Xss-Protection", "DENY");
    context.Response.Headers.Append("X-Frame-Options", "DENY");
    context.Response.Headers.Append("Content-Type", "application/json");
    context.Response.Headers.Append("X-Content-Type-Options", "nosniff");
    context.Response.Headers.Append("Referrer-Policy", "no-referrer");
    context.Response.Headers.Append("Cashe-Control", "no-store");
    context.Response.Headers.Append("Feature-Policy",
    "vibrate 'self' ; " +
    "camera 'self' ; " +
    "microphone 'self' ; " +
    "speaker 'self' https://youtube.com https://www.youtube.com ;" +
    "geolocation 'self' ; " +
    "gyroscope 'self' ; " +
    "magnetometer 'self' ; " +
    "midi 'self' ; " +
    "sync-xhr 'self' ; " +
    "push 'self' ; " +
    "notifications 'self' ; " +
    "fullscreen '*' ; " +
    "payment 'self' ; ");

    context.Response.Headers.Append(
    "Content-Security-Policy",
    "frame-ancestors 'none'; "
    );
    await next();
});

// Strict-Transport-Security
app.UseHsts();


// Configure swagger
if (app.Environment.IsDevelopment() || app.Environment.IsProduction())
{
    app.UseSwagger();
    app.UseSwaggerUI(
    );
}

app.UseHttpsRedirection();

// app.MapIdentityApi<User>();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();


app.Run();


// set Program class to public for integration tests
public partial class Program { }
