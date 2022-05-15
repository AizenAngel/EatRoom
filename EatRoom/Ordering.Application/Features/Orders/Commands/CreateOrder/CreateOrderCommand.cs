using MediatR;
using Ordering.Application.Features.Orders.Commands.DTOs;
using Ordering.Domain.ValueObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Commands.CreateOrder
{
    public class CreateOrderCommand : IRequest<int>
    {
        public Addresses Adresses { get; set; }

        public string Email { get; set; }

        public string BuyerId { get; set; }
        public string Username { get; set; }

        public string DeliveryManId { get; set; }
        public string DeliveryManUsername { get; set; }


        public IEnumerable<OrderItemDTO> OrderItems { get; set; }

        

    }
}
