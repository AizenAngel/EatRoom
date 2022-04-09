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

        Task<Dish> GetDish(string id);

        Task CreateDish(Dish dish);

        Task<bool> UpdateDish(Dish dish);

        Task<bool> DeleteDish(string id);

    }
}
