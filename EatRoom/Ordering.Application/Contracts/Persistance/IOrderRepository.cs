using Ordering.Application.Contracts.Persistant;
using Ordering.Domain.Aggregates;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Ordering.Application.Contracts.Persistance
{
    public interface IOrderRepository : IAsyncRepository<Order>
    {
        Task<IEnumerable<Order>> getOrderByBuyerUsername(string username);
        //Task<IEnumerable<Order>> getOrderByBuyerId(string id);
        //Task<IEnumerable<Order>> getOrdersByDelUsername(string username);
        //Task<IEnumerable<Order>> getOrdersByDelId(string id);
    }
}
