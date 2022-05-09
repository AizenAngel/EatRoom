using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Queries.ModelView
{
    public class OrderItemModelView
    {
        public string ProductName { get; private set; }
        public int ProductId { get; private set; }
        public string PictureUrl { get; private set; }
        public decimal Price { get; private set; }
        public int Units { get; private set; }
    }
}
