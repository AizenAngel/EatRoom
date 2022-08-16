using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ordering.API.Repositories
{
    public interface IOrderRepository
    {

        public Task<IEnumerable<OrderResponse>> GetAllOrdersByState(StateEnum state);

        public Task<Order> CreateOrder(Order order);

        public Task<IEnumerable<OrderResponse>> GetAllOrdersByDeliveredId(string deliveredId);

        public Task<IEnumerable<OrderResponse>> GetAllOrdersByUserId(string userId);

        public Task<OrderResponse> GetOrderById(int id);

        public Task<int> UpdateOrder(Order order);

    }
}
