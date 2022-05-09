using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Queries.ModelView
{
    public class OrderModelView
    {
        public int Id { get; protected set; }
        public string BuyerId { get; private set; }
        public string BuyerUsername { get; private set; }
        public DateTime OrderDate { get; private set; }

        public Domain.ValueObjects.Addresses address;
        /*Information about delivery man*/
        public string DeliveryManId { get; private set; }
        public string DeliveryManUsername { get; private set; }
        /*Potentially more info about accepting delivery etc...*/
        public Domain.Aggregates.OrderStatus Status { get; protected set; }

        public IEnumerable<OrderItemModelView> OrderItems { get; set;}
    }
}
