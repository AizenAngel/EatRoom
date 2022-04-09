using Basket.API.Entities;
using Basket.API.Repository;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace Basket.API.Controllers
{
    [ApiController]
    [Route("api/v1/[controller]")]
    public class BasketController : ControllerBase
    {
        private readonly IBasketRepository _repository;

        public BasketController(IBasketRepository repository)
        {
            _repository = repository;
        }

        [HttpGet("{username}")]
        [ProducesResponseType(typeof(Cart), StatusCodes.Status200OK)]
        public async Task<ActionResult<Cart>> GetBasket(string username)
        {
            var basket = await _repository.getBasket(username);
            return Ok(basket ?? new Cart(username));
        }

        [HttpPut]
        [ProducesResponseType(typeof(Cart), StatusCodes.Status200OK)]
        public async Task<ActionResult<Cart>> UpdateBasket([FromBody] Cart basket)
        {
            return Ok(await _repository.updateBasket(basket));
        }

        [HttpDelete("{username}")]
        [ProducesResponseType(typeof(void), StatusCodes.Status200OK)]
        public async Task<IActionResult> DeleteBasket(string username)
        {
            await _repository.deleteBasket(username);
            return Ok();
        }
    }
}
