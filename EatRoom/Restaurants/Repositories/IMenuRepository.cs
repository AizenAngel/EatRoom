using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Entities;
namespace Restaurants.API.Repositories
{
    public interface IMenuRepository
    {

        Task<IEnumerable<Menu>> GetAllMenus();

        Task<Menu> GetMenu(string id);

        Task CreateMenu(Menu menu);

        Task<bool> UpdateMenu(Menu menu);

        Task<bool> DeleteMenu(string id);
    }
}
