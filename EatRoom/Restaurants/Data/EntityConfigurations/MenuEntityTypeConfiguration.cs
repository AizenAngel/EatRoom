using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Restaurants.API.Entities;
namespace Restaurants.API.Data.EntityConfigurations
{
    public class MenuEntityTypeConfiguration : IEntityTypeConfiguration<Menu>
    {
        public void Configure(EntityTypeBuilder<Menu> builder)
        {
            builder.ToTable("Menu");
            builder.HasKey(o => o.Id);
            builder.Property(o => o.Id).UseHiLo("Menuseq");

            builder.Property<decimal>("Price")
                .HasColumnType("decimal")
                .HasColumnName("Price")
                .IsRequired();
        }
    }
}
