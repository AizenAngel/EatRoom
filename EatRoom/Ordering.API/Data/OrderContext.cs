using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Ordering.API.Data.EntityConfigurations;
using Ordering.API.Entities;

namespace Ordering.API.Data
{
    public class OrderContext:DbContext
    {
        public OrderContext(DbContextOptions options) : base(options)
        {
        }

        public DbSet<Order> orders { get; set; }
        //public DbSet<OrderDish> orderDishes { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // modelBuilder.ApplyConfiguration(new OrderDishEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new OrderEntityTypeConfiguration());

            base.OnModelCreating(modelBuilder);
        }
    }
}
