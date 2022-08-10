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

        public Task CreateOrder(Order order);

        public Task<IEnumerable<Order>> GetAllOrdersByDeliveredId(int deliveredId);

        public Task<IEnumerable<Order>> GetAllOrdersByUserId(int userId);

        public Task UpdateOrder(Order order);

    }
}
