using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Entities;
namespace Restaurants.API.Repositories
{
    public interface IDishRepository
    {
        Task<IEnumerable<Dish>> GetAllDishes();

        Task<Dish> GetDish(int id);

        Task<Dish> CreateDish(Dish dish);

        Task UpdateDish(Dish dish);

        Task<bool> DeleteDish(int id);

        Task<IEnumerable<Dish>> GetDishesByRestaurantId(int restaurantId);

    }
}
