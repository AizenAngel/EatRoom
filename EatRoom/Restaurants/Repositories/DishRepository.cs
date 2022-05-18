using Restaurants.API.Entities;
using Restaurants.API.Data;
using Dapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace Restaurants.API.Repositories
{
    public class DishRepository : IDishRepository
    {

        private readonly RestaurantsContext _context;

        public DishRepository(RestaurantsContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task CreateDish(Dish dish)
        {
            await _context.dishes.AddAsync(dish);
            await _context.SaveChangesAsync();
        }

        public async Task<bool> DeleteDish(int id)
        {
            var dish = await _context.dishes.FindAsync(id);
            if (dish != null)
            {
                _context.dishes.Remove(dish);
                await _context.SaveChangesAsync();
                return true;
            }
            return false;

        }

        public async Task<IEnumerable<Dish>> GetAllDishes()
        {
            return await _context.dishes.Where(o => true).ToListAsync();
        }

        public async Task<Dish> GetDish(int id)
        {
            return await _context.dishes.FindAsync(id);
        }

        public async Task UpdateDish(Dish dish)
        {
            _context.Entry(dish).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }

        public async Task<IEnumerable<Dish>> GetDishesByRestaurantId(int restaurantId)
        {
            return await _context.dishes.Where(o => o.RestaurantId==restaurantId).ToListAsync();
        }

    }
}
