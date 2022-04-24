using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Entities
{
    public class Restaurant
    {
        public int Id { get; set; }
        public string Name { get; set;}
        public string LogoFile { get; set; }

        public List<Menu> menus { get; set; }
    }
}
