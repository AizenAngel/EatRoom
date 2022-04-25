using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Restaurants.API.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateSequence(
                name: "Dishseq",
                incrementBy: 1);
            migrationBuilder.CreateSequence(
                name: "Menuseq",
                incrementBy: 1);
            migrationBuilder.CreateSequence(
                name: "Restaurantseq",
                incrementBy: 1);

            migrationBuilder.CreateTable(
                name: "Restaurant",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo),
                    Name = table.Column<string>(type: "VARCHAR(50)", nullable: false),
                    LogoFile = table.Column<string>(type: "VARCHAR(500000)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Restaurant", x => x.Id);
                });

            migrationBuilder.CreateTable(
            name: "Menu",
            columns: table => new
            {
                Id = table.Column<int>(type: "int", nullable: false).Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo),
                Price = table.Column<double>(type: "decimal", nullable: false),
                RestaurantId = table.Column<int>(type: "int", nullable: false),

            },
            constraints: table =>
            {
                table.PrimaryKey("PK_Menu", x => x.Id);
                table.ForeignKey(
                    name: "FK_Menu_Restaurant_RestaurantId",
                    column: x => x.RestaurantId,
                    principalTable: "Restaurant",
                    principalColumn: "Id");
            });


            migrationBuilder.CreateTable(
                name: "Dish",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false).Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SequenceHiLo),
                    Name = table.Column<string>(type: "VARCHAR(50)", nullable: false),
                    ImageFile = table.Column<string>(type: "VARCHAR(500000)", nullable: true),
                    MenuId= table.Column<int>(type: "int", nullable: true),

                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Dish", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Dish_Menu_MenuId",
                        column: x => x.MenuId,
                        principalTable: "Menu",
                        principalColumn: "Id");
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
             name: "Dish");
            migrationBuilder.DropTable(
                name: "Menu");
            migrationBuilder.DropTable(
            name: "Restaurant");

            migrationBuilder.DropSequence(
                name: "Dishseq");
            migrationBuilder.DropSequence(
                name: "Menuseq");
            migrationBuilder.DropSequence(
                name: "Restaurantseq");
        }
    }
}
