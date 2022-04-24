using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Restaurants.API.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restaurants.API.Data.EntityConfigurations
{
    public class DishEntityTypeConfiguration : IEntityTypeConfiguration<Dish>
    {
        public void Configure(EntityTypeBuilder<Dish> builder)
        {
            builder.ToTable("Dish");
            builder.HasKey(o => o.Id);
            builder.Property(o => o.Id).UseHiLo("Dishseq");

            builder.Property<string>("Name")
                .HasColumnType("VARCHAR(50)")
                .HasColumnName("Name")
                .IsRequired();
            builder.Property<string>("ImageFile")
               .HasColumnType("VARCHAR(50)")
               .HasColumnName("ImageFile")
               .IsRequired();
        }
    }
}
