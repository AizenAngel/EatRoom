using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Domain.Aggregates
{
    public enum OrderStatus
    {
        ACCEPTED,
        PENDING,
        DELIVERED
    }
}
