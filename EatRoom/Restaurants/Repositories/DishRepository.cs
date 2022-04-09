using Restaurants.API.Entities;
using Restaurants.API.Data;
using Dapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Repositories
{
    public class DishRepository : IDishRepository
    {
        private readonly IRestaurantsContext _context;

        public DishRepository(IRestaurantsContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public Task CreateDish(Dish dish)
        {
            throw new NotImplementedException();
        }

        public Task<bool> DeleteDish(string id)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<Dish>> GetAllDishes()
        {
            throw new NotImplementedException();
        }

        public async Task<Dish> GetDish(string id)
        {
            using var connection = _context.GetConnection();

            var dish = await connection.QueryFirstOrDefaultAsync<Dish>(
                "SELECT * FROM Dish WHERE id = @Id", new { Id = id });
            return dish;
        }

        public Task<bool> UpdateDish(Dish dish)
        {
            throw new NotImplementedException();
        }
    }
}
