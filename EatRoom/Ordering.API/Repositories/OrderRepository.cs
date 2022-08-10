using Microsoft.EntityFrameworkCore;
//using Restaurants.API.Data;
using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Ordering.API.Repositories;
using Ordering.API.Data;

namespace Ordering.API.Repositories
{
    public class OrderRepository: IOrderRepository
    {
        private readonly OrderContext _context;

        public OrderRepository(OrderContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public Task<IEnumerable<Order>> GetAllOrdersByState(StateEnum state)
        {
            throw new NotImplementedException();
        }

        public Task CreateOrder(Order order)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<Order>> GetAllOrdersByDeliveredId(int deliveredId)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<Order>> GetAllOrdersByUserId(int userId)
        {
            throw new NotImplementedException();
        }

        public Task UpdateOrder(Order order)
        {
            throw new NotImplementedException();
        }
    }
}
