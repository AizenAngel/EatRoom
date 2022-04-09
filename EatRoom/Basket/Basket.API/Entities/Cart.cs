using System.Collections.Generic;

namespace Basket.API.Entities
{
    public class Cart
    {
        public string Username { get; set; }
        public List<Item> Items { get; set; } = new List<Item>();

        public Cart()
        {
        }

        public Cart(string username)
        {
            Username = username;
        }

        public decimal TotalPrice
        {
            get
            {
                decimal price = 0;
                foreach (Item item in Items)
                {
                    price = price + item.Price;
                }
                return price;
            }
        }
    }
}
