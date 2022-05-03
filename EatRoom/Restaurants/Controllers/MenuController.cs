using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Restaurants.API.Entities;
using Restaurants.API.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Controllers
{
    [Route("api/v1/[controller]")]
    [ApiController]
    public class MenuController : ControllerBase
    {
        private readonly IMenuRepository _repository;

        public MenuController(IMenuRepository repository)
        {
            _repository = repository ?? throw new ArgumentNullException(nameof(repository));
        }

        [HttpGet]
        [ProducesResponseType(typeof(IEnumerable<Menu>), StatusCodes.Status200OK)]
        public async Task<ActionResult<Menu>> GetAllMenus()
        {
            var menus = await _repository.GetAllMenus();
            return Ok(menus);
        }

        [HttpGet("menuId",Name = "GetMenu")]
        [ProducesResponseType(typeof(Menu), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Menu), StatusCodes.Status404NotFound)]
        public async Task<ActionResult<Menu>> GetMenu(string menuId)
        {
            var menu = await _repository.GetMenu(menuId);
            if (menu == null)
            {
                return NotFound(null);
            }
            return Ok(menu);
        }

        [HttpPut]
        [ProducesResponseType(typeof(Menu), StatusCodes.Status200OK)]
        public async Task<IActionResult> UpdateMenu([FromBody] Menu menu)
        {
            await _repository.UpdateMenu(menu);
            return Ok();
        }


        [HttpPost]
        [ProducesResponseType(typeof(IEnumerable<Menu>), StatusCodes.Status201Created)]
        public async Task<ActionResult<Menu>> CreateDish([FromBody] Menu menu)
        {
            await _repository.CreateMenu(menu);

            return CreatedAtRoute("GetMenu", new { id = menu.Id }, menu);
        }

        [HttpDelete("menuId")]
        [ProducesResponseType(typeof(Menu), StatusCodes.Status200OK)]
        public async Task<ActionResult<Menu>> DeleteDish(string menuId)
        {
            return Ok(await _repository.DeleteMenu(menuId));
        }
    }
}
