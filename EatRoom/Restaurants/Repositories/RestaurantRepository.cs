using Microsoft.EntityFrameworkCore;
using Restaurants.API.Data;
using Restaurants.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Repositories
{
    public class RestaurantRepository:IRestaurantRepository
    {
        private readonly RestaurantsContext _context;

        public RestaurantRepository(RestaurantsContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task CreateRestaurant(Restaurant restaurant)
        {
            await _context.restaurants.AddAsync(restaurant);
            await _context.SaveChangesAsync();
        }

        public async Task<bool> DeleteRestaurant(int id)
        {
            var restaurant= await _context.restaurants.FindAsync(id);
            if (restaurant != null)
            {
                _context.restaurants.Remove(restaurant);
                await _context.SaveChangesAsync();
                return true;
            }
            return false;
            
        }

        public async Task<IEnumerable<Restaurant>> GetAllRestaurants()
        {
            return await _context.restaurants.Where(o=>true).ToListAsync();
        }

        public async Task<Restaurant> GetRestaurant(int id)
        {
            return await _context.restaurants.FindAsync(id);
        }

        public async Task UpdateRestaurant(Restaurant restaurant)
        {
            _context.Entry(restaurant).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }
    }
}
