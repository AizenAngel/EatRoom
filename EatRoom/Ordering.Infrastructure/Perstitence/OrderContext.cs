using Microsoft.EntityFrameworkCore;
using Ordering.Domain.Aggregates;
using Ordering.Domain.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Ordering.Infrastructure.Perstitence
{
    public class OrderContext : DbContext
    {
        public OrderContext(DbContextOptions options) 
            : base(options)
        {
        }
        public DbSet<Order> Orders {get; set;}

        public override Task<int> SaveChangesAsync(CancellationToken cancellationToken = default)
        {
            foreach (var entry in ChangeTracker.Entries<EntityBase>())
            {
                switch (entry.State)
                {
                    case EntityState.Added:
                        entry.Entity.CreatedBy = "createdBy";
                        entry.Entity.DateCreated = DateTime.Now;
                        break;
                    case EntityState.Modified:
                        entry.Entity.ModifiedBy = "WhoMofied";
                        entry.Entity.DateModified = DateTime.Now;
                        break;
                }
            }
            return base.SaveChangesAsync(cancellationToken);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
    }
}
