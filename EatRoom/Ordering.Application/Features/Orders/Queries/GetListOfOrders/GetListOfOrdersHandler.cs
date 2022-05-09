using MediatR;
using Ordering.Application.Contracts.Infrasturucture.Factories;
using Ordering.Application.Contracts.Persistance;
using Ordering.Application.Features.Orders.Queries.ModelView;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Queries.GetListOfOrders
{
    public class GetListOfOrdersHandler : IRequestHandler<GetListOfOrdersQuery, List<OrderModelView>>
    {
        private readonly IOrderRepository _orderRepository;
        private readonly IOrderViewFactory _orderFactory;

        public GetListOfOrdersHandler(IOrderRepository orderRepository, IOrderViewFactory orderFactory)
        {
            _orderRepository = orderRepository ?? throw new ArgumentNullException(nameof(orderRepository));
            _orderFactory = orderFactory ?? throw new ArgumentNullException(nameof(orderFactory));
        }

        public async Task<List<OrderModelView>> Handle(GetListOfOrdersQuery request, CancellationToken cancellationToken)
        {
            var orderList = await _orderRepository.getOrderByBuyerUsername(request.Username);
            return orderList.Select(o => _orderFactory.CreateOrderView(o)).ToList();
        }
    }
}
