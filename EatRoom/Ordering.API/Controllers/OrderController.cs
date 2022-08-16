using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Ordering.API.Entities;
using Ordering.API.Repositories;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Ordering.API.Controllers
{
    [Route("api")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        private readonly IOrderRepository _repository;

        public OrderController(IOrderRepository repository)
        {   
            _repository = repository ?? throw new ArgumentNullException(nameof(repository));
        }
        [HttpGet("[controller]/{state}")]
        [ProducesResponseType(typeof(IEnumerable<Order>), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<ActionResult<Order>> GetAllOrdersByState(StateEnum state)
        {
            var orders = await _repository.GetAllOrdersByState(state);
            if (orders == null)
            {
                return BadRequest("Bad state");
            }
            return Ok(orders);
        }

        [HttpPost("[controller]")]
        [ProducesResponseType(typeof(IEnumerable<Order>), StatusCodes.Status201Created)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<ActionResult<Order>> CreateOrder([FromBody] Order order)
        {
            var createdOrder = await _repository.CreateOrder(order);
            if (createdOrder == null)
            {
                return BadRequest();
            }
            return CreatedAtRoute("GetOrder", new { orderId = createdOrder.Id }, createdOrder);
        }

        [HttpGet("[controller]/delivered/{deliveredId}")]
        [ProducesResponseType(typeof(IEnumerable<Order>), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<ActionResult<Order>> GetAllOrdersByDeliveredId(string deliveredId)
        {
            var orders = await _repository.GetAllOrdersByDeliveredId(deliveredId);
            if (orders == null)
            {
                return BadRequest("Bad deliveredId");
            }
            return Ok(orders);
        }

        [HttpGet("[controller]/user/{userId}")]
        [ProducesResponseType(typeof(IEnumerable<Order>), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<ActionResult<Order>> GetAllOrdersByUserId(string userId)
        {
            var orders = await _repository.GetAllOrdersByUserId(userId);
            if (orders == null)
            {
                return BadRequest("Bad userId");
            }
            return Ok(orders);
        }

        [HttpPut("[controller]")]
        [ProducesResponseType(typeof(Order), StatusCodes.Status200OK)]
        [ProducesResponseType(typeof(Nullable), StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> UpdateOrder([FromBody] Order order)
        {
            var response = await _repository.UpdateOrder(order);
            return response == -1 ? BadRequest() : Ok();
        }

        [HttpGet("[controller]/order/{orderId}", Name = "GetOrder")]
        [ProducesResponseType(typeof(Order), StatusCodes.Status200OK)]
        public async Task<ActionResult<Order>> GetOrderById(int orderId)
        {
            var order = await _repository.GetOrderById(orderId);
            return Ok(order);
        }
    }
}
