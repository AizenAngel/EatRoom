using Ordering.Application.Features.Orders.Queries.ModelView;
using Ordering.Domain.Aggregates;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Contracts.Infrasturucture.Factories
{
    public interface IOrderViewFactory
    {
        OrderModelView CreateOrderView(Order order);
    }
}
