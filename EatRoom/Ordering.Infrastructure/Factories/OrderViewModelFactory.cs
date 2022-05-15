using Ordering.Application.Contracts.Infrasturucture.Factories;
using Ordering.Application.Features.Orders.Queries.ModelView;
using Ordering.Domain.Aggregates;
using Ordering.Domain.ValueObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Infrastructure.Factories
{
    public class OrderViewModelFactory : IOrderViewFactory
    {
        public OrderModelView CreateOrderView(Order order)
        {
            var OrderVM = new OrderModelView();
            OrderVM.Id = order.Id;
            OrderVM.BuyerId = order.BuyerId;
            OrderVM.BuyerUsername = order.BuyerUsername;
            OrderVM.address = order.address;
            OrderVM.DeliveryManId = order.DeliveryManId;
            OrderVM.DeliveryManUsername = order.DeliveryManUsername;

            var orderItems = new List<OrderItemModelView>();
            foreach(var o in order.OrderItems)
            {
                var OrderItemVM = new OrderItemModelView();
                OrderItemVM.ProductId = o.ProductId;
                OrderItemVM.ProductName = o.ProductName;
                OrderItemVM.PictureUrl = o.PictureUrl;
                OrderItemVM.Price = o.Price;
                OrderItemVM.Units = o.Units;
                orderItems.Add(OrderItemVM);
            }
            OrderVM.OrderItems = orderItems;
            return OrderVM;

        }
    }
}
