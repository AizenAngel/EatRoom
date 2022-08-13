﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;
using Ordering.API.Data;

namespace Ordering.API.Migrations
{
    [DbContext(typeof(OrderContext))]
    partial class OrderContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 63)
                .HasAnnotation("ProductVersion", "5.0.17")
                .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

            //modelBuilder.HasSequence("OrderDishseq")
            //    .IncrementsBy(10);

            modelBuilder.HasSequence("Orderseq")
                .IncrementsBy(10);

            modelBuilder.Entity("Ordering.API.Entities.Order", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:HiLoSequenceName", "Orderseq")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo);

                    b.Property<int>("DelivererId")
                        .HasColumnType("integer");

                    b.Property<int>("Dishes")
                        .HasColumnType("text");

                    b.Property<int>("State")
                        .HasColumnType("int")
                        .HasColumnName("State");

                    b.Property<int>("UserId")
                        .HasColumnType("integer");

                    b.HasKey("Id");

                    b.ToTable("Order");
                });

            //modelBuilder.Entity("Ordering.API.Entities.OrderDish", b =>
            //    {
            //        b.Property<int>("Id")
            //            .ValueGeneratedOnAdd()
            //            .HasColumnType("integer")
            //            .HasAnnotation("Npgsql:HiLoSequenceName", "OrderDishseq")
            //            .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo);

            //        b.Property<int>("DishId")
            //            .HasColumnType("integer");

            //        b.Property<int>("OrderId")
            //            .HasColumnType("integer");

            //        b.HasKey("Id");

            //        b.ToTable("OrderDish");
            //    });
#pragma warning restore 612, 618
        }
    }
}
