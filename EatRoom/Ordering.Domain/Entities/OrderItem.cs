using Ordering.Domain.Common;
using Ordering.Domain.Exceptions;
using System;

namespace Ordering.Domain.Entities
{
    public class OrderItem : EntityBase
    {
        public string ProductName { get; private set; }
        public int ProductId { get; private set; }
        public string PictureUrl { get; private set; }
        public decimal Price { get; private set; }
        public int Units { get; private set; } = 0;

        public OrderItem(string productName, int productId, string pictureUrl, decimal price, int units)
        {
            ProductName = productName ?? throw new ArgumentNullException(nameof(productName));
            ProductId = productId;
            PictureUrl = pictureUrl ?? throw new ArgumentNullException(nameof(pictureUrl));
            Price = price;
            AddUnits(units);
        }

        public void AddUnits(int units)
        {
            int newUnits = Units + units;

            if (newUnits <= 0)
            {
                throw new OrderingDomainException("Invalid number of units in OrderItem!");
            }
            Units = newUnits;
        }

        public decimal getTotalPriceItem()
        {
            return Price * Units;
        }
    }
}
