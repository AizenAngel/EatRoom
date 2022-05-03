using Microsoft.EntityFrameworkCore.Migrations;

namespace IdentityServer.Migrations
{
    public partial class AddedRolesToDb : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[] { "0971b8c2-bffe-4a62-bd83-8acf372239d1", "d251aab8-d0eb-423d-a6a4-055804621d05", "Customer", "CUSTOMER" });

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[] { "3bb770cc-38e2-4c62-a8e0-f8c7bd90e66f", "942e17ab-6ae2-4998-bc27-736d7625de4c", "Administrator", "ADMINISTRATOR" });

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[] { "6b085001-1c97-478b-b4ae-58ebba0d0b0b", "b54fa789-bf40-401b-aeca-1571ea8104b1", "Deliverer", "DELIVERER" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "0971b8c2-bffe-4a62-bd83-8acf372239d1");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "3bb770cc-38e2-4c62-a8e0-f8c7bd90e66f");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "6b085001-1c97-478b-b4ae-58ebba0d0b0b");
        }
    }
}
