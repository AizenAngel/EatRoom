using Ordering.Application.Contracts.Infrasturucture.Factories;
using Ordering.Application.Features.Orders.Commands.CreateOrder;
using Ordering.Domain.Aggregates;
using Ordering.Domain.ValueObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Infrastructure.Factories
{
    public class OrderFactory : IOrderFactory
    {
        public Order Create(CreateOrderCommand command)
        {
            var order = new Order(command.BuyerId, command.Username, command.Email, command.Adresses, command.DeliveryManId, command.DeliveryManUsername);
            foreach (var item in command.OrderItems)
            {
                order.AddOrderItem(item.ProductName, item.ProductId, item.PictureUrl, item.Price, item.Units);
            }
            return order;
        }
    }
}
