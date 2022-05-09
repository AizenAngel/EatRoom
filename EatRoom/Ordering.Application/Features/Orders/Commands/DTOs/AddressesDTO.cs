using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Commands.DTOs
{
    public class AddressesDTO
    {
        public string StreetName { get; set; }
        public string StreetNumber { get; set; }
        public int ZipCode { get; set; }
        public string CityName { get; set; }
        public string DistrictName { get; set; }
    }
}
