using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Npgsql;

namespace Restaurants.API.Data
{
    public interface IRestaurantsContext
    {
        NpgsqlConnection GetConnection();
    }
}
