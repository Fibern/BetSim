FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
FROM mcr.microsoft.com/dotnet/sdk:8.0 as build

WORKDIR /app

WORKDIR /src
COPY ["src/WebApi/WebApi.csproj", "WebApi/"]
COPY ["src/Application/Application.csproj", "Application/"]
COPY ["src/Domain/Domain.csproj", "Domain/"]
COPY ["src/Infrastructure/Infrastructure.csproj", "Infrastructure/"]

RUN dotnet restore "/src/WebApi/WebApi.csproj"

ENV ConnectionStrings:Dbconnect = "User ID=${DB_USER};Host=${host};Port=5432;Database=${DB_NAME};Password=${DB_PASSWORD};TrustServerCertificate=True;"
COPY /src .
WORKDIR /src/WebApi

RUN dotnet build "WebApi.csproj" -c Relase -o /app/build

FROM build as publish
RUN dotnet publish "WebApi.csproj" -c Relase -o /app/publish /p:UseAppHost=false

FROM base as final
WORKDIR /app
EXPOSE 8080

COPY --from=publish /app/publish .
ENTRYPOINT [ "dotnet", "WebApi.dll" ]


