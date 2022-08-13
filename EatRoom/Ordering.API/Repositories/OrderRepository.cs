using Microsoft.EntityFrameworkCore;
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

        public async Task<IEnumerable<Order>> GetAllOrdersByState(StateEnum state)
        {
            return await _context.orders.Where(o => o.State == state).ToListAsync();
        }

        public async Task<Order> CreateOrder(Order order)
        {
            var createdOrder = await _context.orders.AddAsync(order);
            await _context.SaveChangesAsync();
            return createdOrder.Entity;
        }

        public async Task<IEnumerable<Order>> GetAllOrdersByDeliveredId(int deliveredId)
        {
            return await _context.orders.Where(o => o.DelivererId == deliveredId).ToListAsync();
        }

        public async Task<IEnumerable<Order>> GetAllOrdersByUserId(int userId)
        {
            return await _context.orders.Where(o => o.UserId == userId).ToListAsync();
        }

        public async Task<Order> GetOrderById(int id)
        {
            return await _context.orders.FindAsync(id);
        }

        public async  Task UpdateOrder(Order order)
        {
            _context.Entry(order).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }
    }
}
