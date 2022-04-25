using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Restaurants.API.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Restaurants.API.Data.EntityConfigurations
{
    public class RestaurantEntityTypeConfiguration : IEntityTypeConfiguration<Restaurant>
    {
        public void Configure(EntityTypeBuilder<Restaurant> builder)
        {
            builder.ToTable("Restaurant");
            builder.HasKey(o => o.Id);
            builder.Property(o => o.Id).UseHiLo("Restaurantseq");

            builder.Property<string>("Name")
                .HasColumnType("VARCHAR(50)")
                .HasColumnName("Name")
                .IsRequired();
            builder.Property<string>("LogoFile")
               .HasColumnType("VARCHAR(500000)")
               .HasColumnName("LogoFile");
        }
    }
}
