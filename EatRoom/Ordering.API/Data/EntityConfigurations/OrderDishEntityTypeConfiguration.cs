using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Ordering.API.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Ordering.API.Data.EntityConfigurations
{
    public class OrderDishEntityTypeConfiguration: IEntityTypeConfiguration<OrderDish>
    {
        public void Configure(EntityTypeBuilder<OrderDish> builder)
        {
            builder.ToTable("OrderDish");
            builder.HasKey(o => o.Id);
            builder.Property(o => o.Id).UseHiLo("OrderDishseq").ValueGeneratedOnAdd();
        }
    }
}
