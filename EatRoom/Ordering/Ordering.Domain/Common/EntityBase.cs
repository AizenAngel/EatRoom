using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering.Domain.Common
{
    public abstract class EntityBase
    {
        public int Id { get; protected set; }

        public string CreatedBy { get; private set; }

        public DateTime DateCreated { get; private set; }

        public string ModifiedBy { get; private set; }

        public DateTime? DateModified { get; private set; }

    }
}
