using Basket.API.Entities;
using System.Threading.Tasks;

namespace Basket.API.Repository
{
    public interface IBasketRepository
    {
        Task<Cart> getBasket(string username);
        Task<Cart> updateBasket(Cart basket);
        Task deleteBasket(string username);

    }
}
