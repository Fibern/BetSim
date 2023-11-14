using BetSimApi;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<DbMainContext>(o => o.UseNpgsql(builder.Configuration.GetConnectionString("Dbconnect"),
    builder => builder.EnableRetryOnFailure()));

builder.Services.AddHttpContextAccessor();
builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

//configre authorization
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
   .AddJwtBearer(o =>
   {
       o.TokenValidationParameters = new TokenValidationParameters
       {
           ClockSkew = TimeSpan.FromMinutes(1),
           IgnoreTrailingSlashWhenValidatingAudience = true,
           IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(builder.Configuration.GetSection("TokenOptions: SigningKey").Value!)),
           ValidateIssuerSigningKey = true,
           RequireExpirationTime = true,    
           RequireAudience = true,
           RequireSignedTokens = true,
           ValidateAudience = true,
           ValidateIssuer = true,
           ValidateLifetime = true,
           ValidAudience = "api://my-audience/",
           ValidIssuer = "api://my-issuer/"
       };
   });

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
