using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Queries.ModelView
{
    public class OrderModelView
    {
        public int Id { get; set; }
        public string BuyerId { get; set; }
        public string BuyerUsername { get; set; }
        public DateTime OrderDate { get; set; }

        public Domain.ValueObjects.Addresses address;
        /*Information about delivery man*/
        public string DeliveryManId { get;set; }
        public string DeliveryManUsername { get; set; }
        /*Potentially more info about accepting delivery etc...*/
        public Domain.Aggregates.OrderStatus Status { get; set; }

        public IEnumerable<OrderItemModelView> OrderItems { get; set;}
    }
}
