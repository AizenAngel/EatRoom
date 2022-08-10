using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ordering.API.Entities
{
    public class OrderDish
    {
        public int OrderDishId { get; set; }
        public int OrderId { get; set; }

        public int DishId { get; set; }
    }
}
