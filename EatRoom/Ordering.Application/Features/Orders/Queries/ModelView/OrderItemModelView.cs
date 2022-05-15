using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Queries.ModelView
{
    public class OrderItemModelView
    {
        public string ProductName { get;  set; }
        public int ProductId { get;  set; }
        public string PictureUrl { get;  set; }
        public decimal Price { get;  set; }
        public int Units { get;  set; }
    }
}
