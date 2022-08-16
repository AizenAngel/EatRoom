using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Repositories;
using Restaurants.API.Entities;


namespace Restaurants.API.Controllers
{
    [Route("api/v1/[controller]")]
    [ApiController]
    public class RestaurantController : ControllerBase
    {

        private readonly IRestaurantRepository _repository;

        public RestaurantController(IRestaurantRepository repository)
        {
            _repository = repository ?? throw new ArgumentNullException(nameof(repository));
        }

        [HttpGet]
        [ProducesResponseType(typeof(IEnumerable<Restaurant>), StatusCodes.Status200OK)]
        public async Task<ActionResult<Restaurant>> GetAllRestaurants()
        {
            var restaurants = await _repository.GetAllRestaurants();
            return Ok(restaurants);
        }

        [HttpGet("restaurantId",Name = "GetRestaurant")]
        [ProducesResponseType(typeof(Restaurant), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Restaurant), StatusCodes.Status404NotFound)]
        public async Task<ActionResult<Restaurant>> GetRestaurant(int restaurantId)
        {
            var restaurant = await _repository.GetRestaurant(restaurantId);
            if (restaurant == null)
            {
                return NotFound(null);
            }
            return Ok(restaurant);
        }

        [HttpPut]
        [ProducesResponseType(typeof(Restaurant), StatusCodes.Status200OK)]
        public async Task<IActionResult> UpdateRestaurant([FromBody] Restaurant restaurant)
        {
            await _repository.UpdateRestaurant(restaurant);
            return Ok();
        }


        [HttpPost]
        [ProducesResponseType(typeof(IEnumerable<Restaurant>), StatusCodes.Status201Created)]
        public async Task<ActionResult<Restaurant>> CreateRestaurant([FromBody] Restaurant restaurant)
        {
            await _repository.CreateRestaurant(restaurant);
            return CreatedAtRoute("GetRestaurant", new { id = restaurant.Id }, restaurant);
        }

        [HttpDelete("restaurantId")]
        [ProducesResponseType(typeof(Restaurant), StatusCodes.Status200OK)]
        public async Task<ActionResult<Restaurant>> DeleteRestaurant(int restautantId)
        {
            return Ok(await _repository.DeleteRestaurant(restautantId));
        }
    }
}
