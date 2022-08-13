using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Ordering.API.Migrations
{
    public partial class OrderTableCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            //migrationBuilder.CreateSequence(
            //    name: "OrderDishseq",
            //    incrementBy: 10);

            migrationBuilder.CreateSequence(
                name: "Orderseq",
                incrementBy: 10);

            migrationBuilder.CreateTable(
                name: "Order",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo),
                    UserId = table.Column<string>(type: "varchar (450)", nullable: false),
                    Dishes = table.Column<string>(type: "text", nullable: false),
                    State = table.Column<int>(type: "int", nullable: false),
                    DelivererId = table.Column<string>(type: "varchar (450)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Order", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "OrderDish",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo),
                    OrderId = table.Column<int>(type: "integer", nullable: false),
                    DishId = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_OrderDish", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Order");

            //migrationBuilder.DropTable(
            //    name: "OrderDish");

            //migrationBuilder.DropSequence(
            //    name: "OrderDishseq");

            migrationBuilder.DropSequence(
                name: "Orderseq");
        }
    }
}
