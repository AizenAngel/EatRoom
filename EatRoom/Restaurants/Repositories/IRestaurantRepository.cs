using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Entities;
namespace Restaurants.API.Repositories
{
    interface IRestaurantRepository
    {

        Task<IEnumerable<Restaurant>> GetAllRestaurants();

        Task<Restaurant> GetRestaurant(string id);

        Task CreateRestaurant(Restaurant restaurant);

        Task<bool> UpdateRestaurant(Restaurant restaurant);

        Task<bool> DeleteRestauran(string id);
    }
}
