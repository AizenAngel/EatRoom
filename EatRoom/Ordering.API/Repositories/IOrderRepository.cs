using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ordering.API.Repositories
{
    public interface IOrderRepository
    {

        public Task<IEnumerable<Order>> GetAllOrdersByState(StateEnum state);

        public Task<Order> CreateOrder(Order order);

        public Task<IEnumerable<Order>> GetAllOrdersByDeliveredId(string deliveredId);

        public Task<IEnumerable<Order>> GetAllOrdersByUserId(string userId);

        public Task<Order> GetOrderById(int id);

        public Task<int> UpdateOrder(Order order);

    }
}
