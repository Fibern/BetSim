using Domain.Entities;

namespace Application.Abstractions
{
    public interface IPushNotificationService
    {
        public Task SendNotificationAboutNewScore(Bet bet, string deviceToken);
        public Task SendNotyficationPointsPayload(double points, string deviceToken);
    }
}