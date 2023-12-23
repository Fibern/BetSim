using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class addidtoonetoone : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Bet_Coupon_CouponId",
                table: "Bet");

            migrationBuilder.DropTable(
                name: "EventUser");

            migrationBuilder.DropColumn(
                name: "Date",
                table: "Offert");

            migrationBuilder.AddColumn<DateTime>(
                name: "DateTime",
                table: "Offert",
                type: "timestamp with time zone",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<bool>(
                name: "Active",
                table: "Event",
                type: "boolean",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<int>(
                name: "OwnerId",
                table: "Event",
                type: "integer",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<DateTime>(
                name: "DateTime",
                table: "Coupon",
                type: "timestamp with time zone",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AlterColumn<int>(
                name: "CouponId",
                table: "Bet",
                type: "integer",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "integer",
                oldNullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Event_OwnerId",
                table: "Event",
                column: "OwnerId");

            migrationBuilder.AddForeignKey(
                name: "FK_Bet_Coupon_CouponId",
                table: "Bet",
                column: "CouponId",
                principalTable: "Coupon",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Event_AspNetUsers_OwnerId",
                table: "Event",
                column: "OwnerId",
                principalTable: "AspNetUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Bet_Coupon_CouponId",
                table: "Bet");

            migrationBuilder.DropForeignKey(
                name: "FK_Event_AspNetUsers_OwnerId",
                table: "Event");

            migrationBuilder.DropIndex(
                name: "IX_Event_OwnerId",
                table: "Event");

            migrationBuilder.DropColumn(
                name: "DateTime",
                table: "Offert");

            migrationBuilder.DropColumn(
                name: "Active",
                table: "Event");

            migrationBuilder.DropColumn(
                name: "OwnerId",
                table: "Event");

            migrationBuilder.DropColumn(
                name: "DateTime",
                table: "Coupon");

            migrationBuilder.AddColumn<string>(
                name: "Date",
                table: "Offert",
                type: "text",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AlterColumn<int>(
                name: "CouponId",
                table: "Bet",
                type: "integer",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "integer");

            migrationBuilder.CreateTable(
                name: "EventUser",
                columns: table => new
                {
                    AdministratorsId = table.Column<int>(type: "integer", nullable: false),
                    EventsCreatedId = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EventUser", x => new { x.AdministratorsId, x.EventsCreatedId });
                    table.ForeignKey(
                        name: "FK_EventUser_AspNetUsers_AdministratorsId",
                        column: x => x.AdministratorsId,
                        principalTable: "AspNetUsers",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EventUser_Event_EventsCreatedId",
                        column: x => x.EventsCreatedId,
                        principalTable: "Event",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_EventUser_EventsCreatedId",
                table: "EventUser",
                column: "EventsCreatedId");

            migrationBuilder.AddForeignKey(
                name: "FK_Bet_Coupon_CouponId",
                table: "Bet",
                column: "CouponId",
                principalTable: "Coupon",
                principalColumn: "Id");
        }
    }
}
