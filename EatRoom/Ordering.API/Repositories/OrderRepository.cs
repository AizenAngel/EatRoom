using Microsoft.EntityFrameworkCore;
using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Ordering.API.Repositories;
using Ordering.API.Data;
using System.Net.Http;


namespace Ordering.API.Repositories
{
    public class OrderRepository: IOrderRepository
    {
        private readonly OrderContext _context;
        private static readonly HttpClient client = new HttpClient();
        private static readonly string IdentityURL = "http://host.docker.internal:8001";

        public OrderRepository(OrderContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<IEnumerable<Order>> GetAllOrdersByState(StateEnum state)
        {
            return await _context.orders.Where(o => o.State == state).ToListAsync();
        }

        public async Task<Order> CreateOrder(Order order)
        {
            var userExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.UserId}");
            var delivererExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.DelivererId}");
            if (!userExists.IsSuccessStatusCode || !delivererExists.IsSuccessStatusCode)
            {
                return null;
            }

            var createdOrder = await _context.orders.AddAsync(order);
            await _context.SaveChangesAsync();
            return createdOrder.Entity;
        }

        public async Task<IEnumerable<Order>> GetAllOrdersByDeliveredId(string deliveredId)
        {
            return await _context.orders.Where(o => o.DelivererId == deliveredId).ToListAsync();
        }

        public async Task<IEnumerable<Order>> GetAllOrdersByUserId(string userId)
        {
            return await _context.orders.Where(o => o.UserId == userId).ToListAsync();
        }

        public async Task<Order> GetOrderById(int id)
        {
            return await _context.orders.FindAsync(id);
        }

        public async  Task<int> UpdateOrder(Order order)
        {
            var userExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.UserId}");
            var delivererExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.DelivererId}");
            if (!userExists.IsSuccessStatusCode || !delivererExists.IsSuccessStatusCode)
            {
                return -1;
            }

            var deleteOrder = await _context.orders.FindAsync(order.Id);
            if (deleteOrder != null)
            {
                _context.orders.Remove(deleteOrder);
                await _context.SaveChangesAsync();
                await _context.orders.AddAsync(order);
                int updated = await _context.SaveChangesAsync();

                return updated;
            }

            return -1;
        }
    }
}
