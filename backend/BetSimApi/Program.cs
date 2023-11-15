using BetSimApi;
using BetSimApi.Abstracions;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Npgsql;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add Framework services.
builder.Services.AddDbContext<DbMainContext>(o => o.UseNpgsql(builder.Configuration.GetConnectionString("Dbconnect"),
    builder => builder.EnableRetryOnFailure()));


builder.Services.AddAutoMapper(typeof(Program).Assembly);
builder.Services.AddHttpContextAccessor();
builder.Services.AddControllers();

//inject mediatR
builder.Services.AddMediatR(cfg => cfg.RegisterServicesFromAssembly(typeof(Program).Assembly));

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

//app services
builder.Services.AddScoped<IConnectionFactory,PostgresConnectionFactory>();

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
