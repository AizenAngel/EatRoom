using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace Restaurants.API.Entities
{
    public class Dish
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string ImageFile { get; set; }
        public decimal Price { get; set; }

        public int RestaurantId { get; set; }
        public Restaurant restaurant { get; set; }
    }
}
