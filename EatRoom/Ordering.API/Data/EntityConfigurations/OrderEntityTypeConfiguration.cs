using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Ordering.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ordering.API.Data.EntityConfigurations
{
    public class OrderEntityTypeConfiguration : IEntityTypeConfiguration<Order>
    {
        public void Configure(EntityTypeBuilder<Order> builder)
        {
            builder.ToTable("Order");
            builder.HasKey(o => o.Id);
            builder.Property(o => o.Id).UseHiLo("Orderseq").ValueGeneratedOnAdd();
            builder.Property<decimal>("Price")
                            .HasColumnType("decimal")
                            .HasColumnName("Price")
                            .IsRequired();
            builder.Property<StateEnum>("State")
                            .HasColumnType("int")
                            .HasColumnName("State")
                            .IsRequired();
        }
    }
}