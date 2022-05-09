using Microsoft.EntityFrameworkCore;
using Ordering.Application.Contracts.Persistance;
using Ordering.Domain.Aggregates;
using Ordering.Infrastructure.Perstitence;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Infrastructure.Repositories
{
    public class OrderRepository : RepositoryBase<Order>, IOrderRepository
    {
        public OrderRepository(OrderContext dbContext) : base(dbContext)
        {
        }

        public async Task<IEnumerable<Order>> getOrderByBuyerUsername(string username)
        {
            return await _dbContext.Orders
                .Where(o => o.BuyerUsername == username)
                .Include(o => o.OrderItems)
                .ToListAsync();
        }
    }
}