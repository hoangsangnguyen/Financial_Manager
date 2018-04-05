using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Financial_Webservice.Migrations
{
    public partial class update_jars_type : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Types_Jars_jarID",
                table: "Types");

            migrationBuilder.DropIndex(
                name: "IX_Types_jarID",
                table: "Types");

            migrationBuilder.DropColumn(
                name: "jarID",
                table: "Types");

            migrationBuilder.AddColumn<Guid>(
                name: "typeID",
                table: "Jars",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.CreateIndex(
                name: "IX_Jars_typeID",
                table: "Jars",
                column: "typeID");

            migrationBuilder.AddForeignKey(
                name: "FK_Jars_Types_typeID",
                table: "Jars",
                column: "typeID",
                principalTable: "Types",
                principalColumn: "_id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Jars_Types_typeID",
                table: "Jars");

            migrationBuilder.DropIndex(
                name: "IX_Jars_typeID",
                table: "Jars");

            migrationBuilder.DropColumn(
                name: "typeID",
                table: "Jars");

            migrationBuilder.AddColumn<Guid>(
                name: "jarID",
                table: "Types",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.CreateIndex(
                name: "IX_Types_jarID",
                table: "Types",
                column: "jarID",
                unique: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Types_Jars_jarID",
                table: "Types",
                column: "jarID",
                principalTable: "Jars",
                principalColumn: "_id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
