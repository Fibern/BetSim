using Application.Abstractions;
using Domain.Entities;
using Domain.Enums;
using FirebaseAdmin.Messaging;
using FirebaseCoreAdmin;
using Serilog;

namespace Infrastructure
{
    class PushNotyficationService : IPushNotificationService
    {
        private readonly ILogger _logger;

        public PushNotyficationService(ILogger logger)
        {
            _logger = logger;
        }

        public async Task SendNotificationAboutNewScore(Bet bet, string deviceToken){

            Offert offert = bet.PredictedWinner.Offert;
            if(offert.Type == BetType.Match)
            {
                await SendNotificationMatchOver("Koniec Meczu !", $"Mecz {offert.Title} zakończył się wynikiem {offert.Score}",deviceToken);
                return;
            }

            if(offert.Type == BetType.MultipleSelect){
                await SendNotificationMatchOver("Zakład dobiegł końca !", $"W zakładzie {offert.Title} poprawną odpowiedzią było {offert.Score}",deviceToken);
                return;
            }
        }

        private async Task SendNotificationMatchOver(string title, string message, string deviceToken)
        {
            //create notyfication
            var firebaseMessage = new Message()
            {
                Notification = new Notification
                {                    Title = title,
                    Body = message
                },
                Token = deviceToken
            };   

            var messaging = FirebaseMessaging.DefaultInstance;
            var result = await messaging.SendAsync(firebaseMessage);  

            
            if (!string.IsNullOrEmpty(result))
            {
                // Message was sent successfully
                _logger.Information("Successfully sent Notyfication to {deviceToken}", deviceToken);
            }
            else
            {
                // There was an error sending the message
                _logger.Error("Error sending Notyfication to {deviceToken}", deviceToken);
            }       
        }

        public async Task SendNotyficationPointsPayload(double points, string deviceToken)
        {
            //create notyfication
            var firebaseMessage = new Message()
            {
                Data = new Dictionary<string, string>()
                {
                    {"points",points.ToString()}
                },
                Token = deviceToken
            
            };

            var messaging = FirebaseMessaging.DefaultInstance;
            var result = await messaging.SendAsync(firebaseMessage);    

            if (!string.IsNullOrEmpty(result))
            {
                // Message was sent successfully
                _logger.Information("Successfully sent payload to {deviceToken}", deviceToken);
            }
            else
            {
                // There was an error sending the message
                _logger.Error("Error sending payload to {deviceToken}", deviceToken);
            }           
        }
    }
}