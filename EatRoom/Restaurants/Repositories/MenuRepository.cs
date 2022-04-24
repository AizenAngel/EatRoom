using Microsoft.EntityFrameworkCore;
using Restaurants.API.Data;
using Restaurants.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Repositories
{
    public class MenuRepository : IMenuRepository
    {
        private readonly RestaurantsContext _context;

        public MenuRepository(RestaurantsContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task CreateMenu(Menu menu)
        {
            await _context.menus.AddAsync(menu);
            await _context.SaveChangesAsync();
        }

        public async Task<bool> DeleteMenu(string id)
        {
            var menu = await _context.menus.FindAsync(id);
            if (menu != null)
            {
                _context.menus.Remove(menu);
                await _context.SaveChangesAsync();
                return true;
            }
            return false;
        }

        public async Task<IEnumerable<Menu>> GetAllMenus()
        {
            return await _context.menus.Where(o => true).ToListAsync();
        }

        public async Task<Menu> GetMenu(string id)
        {
            return await _context.menus.FindAsync(id);
        }

        public async Task UpdateMenu(Menu menu)
        {
            _context.Entry(menu).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }
    }
}
