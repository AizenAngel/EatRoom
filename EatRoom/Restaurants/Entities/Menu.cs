using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Entities
{
    public class Menu
    {
        public int Id;
        public List<Dish> Dishes { get; set; }
    }
}
