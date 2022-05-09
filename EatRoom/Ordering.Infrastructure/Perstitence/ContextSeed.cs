using Microsoft.Extensions.Logging;
using Ordering.Domain.Aggregates;
using Ordering.Domain.ValueObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Infrastructure.Perstitence
{
    public class ContextSeed
    {
        public static async Task SeedAsync(OrderContext orderContext, ILogger<ContextSeed> logger)
        {
            if (!orderContext.Orders.Any())
            {
                orderContext.Orders.AddRange((IEnumerable<Domain.Aggregates.Order>)GetPreconfiguredOrders());
                await orderContext.SaveChangesAsync();
                logger.LogInformation("Seeding database associated with context {DbContextName}", nameof(OrderContext));
            }
        }

        private static IEnumerable<Order> GetPreconfiguredOrders()
        {
            var order1 = new Order("1", "maja", "maja@gmail.com", new Addresses("Nikole Pasica", "51", 31000, "Uzice", "Srbija"), "123dm", "mrcDeliveryman");
            order1.AddOrderItem("IPhone X", 1, "https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-x-new-1.jpg", 800, 2);
            order1.AddOrderItem("HTC U11+ Plus", 2, "https://fdn2.gsmarena.com/vv/pics/htc/htc-u11-plus-1.jpg", 380, 1);

            return new List<Order> { order1 };
        }
    }
}
