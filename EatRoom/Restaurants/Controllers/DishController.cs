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

    [ApiController]
    [Route("api/v1/")]
    public class DishController : ControllerBase
    {
        private readonly IDishRepository _repository;
        private readonly IRestaurantRepository _resRepository;


        public DishController(IDishRepository repository, IRestaurantRepository resRepository)
        {
            _repository = repository ?? throw new ArgumentNullException(nameof(repository));
            _resRepository = resRepository ?? throw new ArgumentNullException(nameof(resRepository));
        }

        [HttpGet("[controller]")]
        [ProducesResponseType(typeof(IEnumerable<Dish>), StatusCodes.Status200OK)]
        public async Task<ActionResult<Dish>> GetAllDishes()
        {
            var dishes = await _repository.GetAllDishes();
            return Ok(dishes);
        }

        [HttpGet("[controller]/{dishId}", Name = "GetDish")]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status404NotFound)]
        public async Task<ActionResult<Dish>> GetDish(int dishId)
        {
            var dish = await _repository.GetDish(dishId);
            if (dish == null)
            {
                return NotFound(null);
            }
            return Ok(dish);
        }

        [HttpPut("[controller]")]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status200OK)]
        public async Task<IActionResult> UpdateDish([FromBody] Dish dish)
        {
            await _repository.UpdateDish(dish);
            return Ok();
        }


        [HttpPost("[controller]")]
        [ProducesResponseType(typeof(IEnumerable<Dish>), StatusCodes.Status201Created)]
        public async Task<ActionResult<Dish>> CreateDish([FromBody] Dish dish)
        {
            var createdDish = await _repository.CreateDish(dish);

            return CreatedAtRoute("GetDish", new { dishId = createdDish.Id }, createdDish);
        }

        [HttpDelete("[controller]/{dishId}")]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status200OK)]
        public async Task<ActionResult<Dish>> DeleteDish(int dishId)
        {
            return Ok(await _repository.DeleteDish(dishId));
        }

        [HttpGet("Restaurants/{restaurantId}/[controller]")]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Dish), StatusCodes.Status404NotFound)]
        public async Task<ActionResult<Dish>> GetDishesByRestaurant(int restaurantId)
        {
            var restaurant = await _repository.GetDishesByRestaurantId(restaurantId);
            if (restaurant == null)
            {
                return NotFound(null);
            }
            return Ok(restaurant);
        }

        [HttpGet("dish/getRestaurantByDishId/{dishId}")]
        [ProducesResponseType(typeof(string), StatusCodes.Status200OK)]
        public async Task<ActionResult<string>> GetRestaurantByDishId(int dishId)
        {
            var dish = await _repository.GetDish(dishId);
            var restaurant = await _resRepository.GetRestaurant(dish.RestaurantId);

            return Ok(restaurant.Name);
        }
    }
}
