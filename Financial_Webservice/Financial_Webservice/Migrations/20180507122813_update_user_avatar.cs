using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Financial_Webservice.Migrations
{
    public partial class update_user_avatar : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "avatarUrl",
                table: "Users",
                nullable: true);

            migrationBuilder.AlterColumn<long>(
                name: "size",
                table: "Images",
                nullable: false,
                oldClrType: typeof(int));
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "avatarUrl",
                table: "Users");

            migrationBuilder.AlterColumn<int>(
                name: "size",
                table: "Images",
                nullable: false,
                oldClrType: typeof(long));
        }
    }
}
