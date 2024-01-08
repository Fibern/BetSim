using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class couponchang : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Title",
                table: "Offert");

            migrationBuilder.AddColumn<double>(
                name: "OddSum",
                table: "Coupon",
                type: "double precision",
                nullable: false,
                defaultValue: 0.0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "OddSum",
                table: "Coupon");

            migrationBuilder.AddColumn<string>(
                name: "Title",
                table: "Offert",
                type: "text",
                nullable: false,
                defaultValue: "");
        }
    }
}
