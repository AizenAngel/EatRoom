using Basket.API.Entities;
using Microsoft.Extensions.Caching.Distributed;
using Newtonsoft.Json;
using System.Threading.Tasks;

namespace Basket.API.Repository
{
    public class BasketRepository : IBasketRepository
    {
        private readonly IDistributedCache _cache;

        public BasketRepository(IDistributedCache cache)
        {
            _cache = cache;
        }

        public async Task deleteBasket(string username)
        {
            await _cache.RemoveAsync(username);
        }

        public async Task<Cart> getBasket(string username)
        {
            var basket = await _cache.GetStringAsync(username);
            return JsonConvert.DeserializeObject<Cart>(basket);
        }

        public async Task<Cart> updateBasket(Cart basket)
        {
            var basketString = JsonConvert.SerializeObject(basket);
            await _cache.SetStringAsync(basket.Username, basketString);
            return await getBasket(basket.Username);
        }
    }
}
