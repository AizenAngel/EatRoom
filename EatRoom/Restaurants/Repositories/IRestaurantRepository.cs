using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Entities;
namespace Restaurants.API.Repositories
{
    public interface IRestaurantRepository
    {

        Task<IEnumerable<Restaurant>> GetAllRestaurants();

        Task<Restaurant> GetRestaurant(int id);

        Task CreateRestaurant(Restaurant restaurant);

        Task UpdateRestaurant(Restaurant restaurant);

        Task<bool> DeleteRestaurant(int id);
    }
}
