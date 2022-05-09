using System;

namespace Ordering.Domain.Common
{
    public abstract class EntityBase
    {
        public int Id { get; protected set; }

        public string CreatedBy { get; set; }

        public DateTime DateCreated { get; set; }

        public string ModifiedBy { get; set; }

        public DateTime? DateModified { get; set; }

    }
}
