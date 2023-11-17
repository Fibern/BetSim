using AutoMapper;
using BetSimApi.Abstracions;
using BetSimApi.Model;
using BetSimApi.ViewModel;
using Dapper;
using MediatR;

namespace BetSimApi.Queries.Handlers
{
    public class GetAllEventsHandler : IRequestHandler<GetAllEventsQuery, List<EventViewModel>>
    {
        private DbMainContext _dbContext;
        private IConnectionFactory _connectionFactory;
        private IMapper _mapper;

        public GetAllEventsHandler(DbMainContext dbContext, IMapper mapper, IConnectionFactory connection)
        {
            _dbContext = dbContext;
            _mapper = mapper;
            _connectionFactory = connection;
        }

        public async Task<List<EventViewModel>> Handle(GetAllEventsQuery request, CancellationToken cancellationToken)
        {
            await using var connection = _connectionFactory.CreateConnection();

            var all = await connection.QueryAsync<Event>("select * from \"Event\" ");
            connection.Dispose();

            return _mapper.Map<List<EventViewModel>>(all);
        }
    }
}
