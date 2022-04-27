using Ordering.Domain.Common;
using System;
using System.Collections.Generic;

namespace Ordering.Domain.ValueObjects
{
    public class Addresses : ValueObject
    {
        /*Important informations about addresses in Serbia: Street name, number, zipcode, city, district*/

        public string StreetName { get; private set; }
        public string StreetNumber { get; private set; }
        public int ZipCode { get; private set; }
        public string CityName { get; private set; }
        public string DistrictName { get; private set; }

        public Addresses(string streetName, string streetNumber, int zipCode, string cityName, string districtName)
        {
            StreetName = streetName ?? throw new ArgumentNullException(nameof(streetName));
            StreetNumber = streetNumber ?? throw new ArgumentNullException(nameof(streetNumber));
            ZipCode = zipCode;
            CityName = cityName ?? throw new ArgumentNullException(nameof(cityName));
            DistrictName = districtName ?? throw new ArgumentNullException(nameof(districtName));
        }

        protected override IEnumerable<object> GetEqualityComponents()
        {
            yield return StreetName;
            yield return StreetNumber;
            yield return ZipCode;
            yield return CityName;
            yield return DistrictName;
        }
    }
}
