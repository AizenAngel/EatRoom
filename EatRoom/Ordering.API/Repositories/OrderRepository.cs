using Microsoft.EntityFrameworkCore;
using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Ordering.API.Repositories;
using Ordering.API.Data;
using System.Net.Http;


namespace Ordering.API.Repositories
{
    public class OrderRepository: IOrderRepository
    {
        private readonly OrderContext _context;
        private static readonly HttpClient client = new HttpClient();
        private static readonly string IdentityURL = "http://host.docker.internal:8001";
        private static readonly string RestaurantURL = "http://host.docker.internal:8002";

        public OrderRepository(OrderContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<IEnumerable<OrderResponse>> GetAllOrdersByState(StateEnum state)
        {
            List<Order> orders = await _context.orders.Where(o => o.State == state).ToListAsync();
            List<OrderResponse> orderResponse = new List<OrderResponse>();
            foreach (var order in orders)
            {
                orderResponse.Add(new OrderResponse{ Id = order.Id, UserId = order.UserId, Dishes = order.Dishes, State = order.State, DelivererId = order.DelivererId, RestaurantName = "" });
                var dishId = Int32.Parse(order.Dishes.Split(',')[0]);
                var restaurantName = await client.GetAsync($"{OrderRepository.RestaurantURL}/api/v1/dish/getRestaurantByDishId/{dishId}").Result.Content.ReadAsStringAsync();
                orderResponse.Last().RestaurantName = restaurantName;
            }

            return orderResponse;
        }

        public async Task<Order> CreateOrder(Order order)
        {
            var userExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.UserId}");
            if (!userExists.IsSuccessStatusCode)
            {
                return null;
            }

            var createdOrder = await _context.orders.AddAsync(order);
            await _context.SaveChangesAsync();
            return createdOrder.Entity;
        }

        public async Task<IEnumerable<OrderResponse>> GetAllOrdersByDeliveredId(string deliveredId)
        {
             List<OrderResponse> orderResponse = new List<OrderResponse>();
             var order = await _context.orders.Where(o => o.DelivererId == deliveredId).ToListAsync();
             if (order == null)
            {
                return null;
            }
             var dishId = Int32.Parse(order[0].Dishes.Split(',')[0]);
             var restaurantName = await client.GetAsync($"{OrderRepository.RestaurantURL}/api/v1/dish/getRestaurantByDishId/{dishId}").Result.Content.ReadAsStringAsync();
             orderResponse.Add(new OrderResponse { Id = order[0].Id, State = order[0].State, UserId = order[0].UserId, Dishes = order[0].Dishes, DelivererId = order[0].DelivererId, RestaurantName = restaurantName });
             return orderResponse;
        }

        public async Task<IEnumerable<OrderResponse>> GetAllOrdersByUserId(string userId)
        {
            var order = await _context.orders.Where(o => o.UserId == userId).ToListAsync();
            if (order == null)
            {
                return null;
            }
            List<OrderResponse> orderResponse = new List<OrderResponse>();
            var dishId = Int32.Parse(order[0].Dishes.Split(',')[0]);
            var restaurantName = await client.GetAsync($"{OrderRepository.RestaurantURL}/api/v1/dish/getRestaurantByDishId/{dishId}").Result.Content.ReadAsStringAsync();
            orderResponse.Add(new OrderResponse { Id = order[0].Id, State = order[0].State, UserId = order[0].UserId, Dishes = order[0].Dishes, DelivererId = order[0].DelivererId, RestaurantName = restaurantName });
            return orderResponse;
        }

        public async Task<OrderResponse> GetOrderById(int id)
        {
            var order = await _context.orders.FindAsync(id);
            if (order == null)
            {
                return null;
            }
            var dishId = Int32.Parse(order.Dishes.Split(',')[0]);
            var restaurantName = await client.GetAsync($"{OrderRepository.RestaurantURL}/api/v1/dish/getRestaurantByDishId/{dishId}").Result.Content.ReadAsStringAsync();
            var orderResponse = new OrderResponse { Id = order.Id, State = order.State, UserId = order.UserId, Dishes = order.Dishes, DelivererId = order.DelivererId, RestaurantName = restaurantName };
            return orderResponse;
        }

        public async  Task<int> UpdateOrder(Order order)
        {
            var userExists = await client.GetAsync($"{OrderRepository.IdentityURL}/api/v1/User/user/{order.UserId}");
            if (!userExists.IsSuccessStatusCode)
            {
                return -1;
            }

            var deleteOrder = await _context.orders.FindAsync(order.Id);
            if (deleteOrder != null)
            {
                _context.orders.Remove(deleteOrder);
                await _context.SaveChangesAsync();
                await _context.orders.AddAsync(order);
                int updated = await _context.SaveChangesAsync();

                return updated;
            }

            return -1;
        }
    }
}
