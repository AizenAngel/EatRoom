using Ordering.Domain.Common;
using Ordering.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Domain.Aggregates
{
    public class Order : AggregateRoot
    {
        public string BuyerId { get; private set; }
        public string BuyerUsername { get; private set; }
        public DateTime OrderDate { get; private set; }

        /*Information about delivery user*/
        public string DeliveryManId { get; private set; }
        public string DeliveryManUsername { get; private set; }
        /*Potentially more info about accepting delivery etc...*/
        public DateTime DeliveryModified { get; private set; }

        public OrderStatus Status { get; protected set; }

        private readonly List<OrderItem> _orderItems = new List<OrderItem>();
        public IReadOnlyCollection<OrderItem> OrderItems => _orderItems;

        public Order(string buyerId, string buyerUsername, string deliveryManId, string deliveryManUsername)
        {
            BuyerId = buyerId ?? throw new ArgumentNullException(nameof(buyerId));
            BuyerUsername = buyerUsername ?? throw new ArgumentNullException(nameof(buyerUsername));
            DeliveryManId = deliveryManId ?? throw new ArgumentNullException(nameof(deliveryManId));
            DeliveryManUsername = deliveryManUsername ?? throw new ArgumentNullException(nameof(deliveryManUsername));
            OrderDate = DateTime.Now;
            DeliveryModified = DateTime.Now;
            Status = OrderStatus.ACCEPTED;
        }

        public Order(int id, string buyerId, string buyerUsername, string deliveryManId, string deliveryManUsername)
                :this(buyerId, buyerUsername, deliveryManId, deliveryManUsername)
        {
            Id = id;   
        }

        public Order(int id)
        {
            Id = id;
        }

        public void AddOrderItem(string productName, int productId, string pictureUrl, decimal price, int units=1)
        {
            var existingOrderItem = OrderItems.Where(o => o.ProductId == productId).SingleOrDefault();

            if(existingOrderItem == null)
            {
                /* OrderItem is not in Order and we have to make it*/
                var item = new OrderItem(productName, productId, pictureUrl, price, units);
                _orderItems.Add(item);
            }else
            {
                /*OrderItem exists inside of Order, so we should just change number of units*/
                existingOrderItem.AddUnits(units);
            }
        }
        public decimal getTotalPrice()
        {
            return _orderItems.Sum(o => o.getTotalPriceItem());
        }
    }
}
