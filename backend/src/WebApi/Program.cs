using Application;
using Domain.Entities;
using Infrastructure;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Options;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddApplication();
builder.Services.AddInfraStucture(builder.Configuration);

builder.Services.AddHttpContextAccessor();
builder.Services.AddControllers();

//configure identity
builder.Services.AddIdentityCore<User>()
    .AddEntityFrameworkStores<DbMainContext>()
    .AddApiEndpoints();

//configre authorization
builder.Services.AddAuthentication()
   .AddBearerToken(IdentityConstants.BearerScheme);

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
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.MapIdentityApi<User>().RequireAuthorization();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers().RequireAuthorization();


app.Run();


// set Program class to public for integration tests
public partial class Program { }
