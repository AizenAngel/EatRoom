using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Npgsql;
using Restaurants.API.Data.EntityConfigurations;
using Restaurants.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Data
{
    public class RestaurantsContext:DbContext
    {

        public RestaurantsContext(DbContextOptions options) : base(options)
        {
        }

        public DbSet<Restaurant> restaurants { get; set; }
        public DbSet<Dish> dishes { get; set; }
        public DbSet<Menu> menus { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new RestaurantEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new MenuEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new DishEntityTypeConfiguration());

            base.OnModelCreating(modelBuilder);
        }
    }
}
