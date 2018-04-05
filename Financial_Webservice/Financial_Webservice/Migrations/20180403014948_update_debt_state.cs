using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Financial_Webservice.Migrations
{
    public partial class update_debt_state : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_States_Debts_debtID",
                table: "States");

            migrationBuilder.DropIndex(
                name: "IX_States_debtID",
                table: "States");

            migrationBuilder.DropColumn(
                name: "debtID",
                table: "States");

            migrationBuilder.AddColumn<Guid>(
                name: "stateID",
                table: "Debts",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.CreateIndex(
                name: "IX_Debts_stateID",
                table: "Debts",
                column: "stateID");

            migrationBuilder.AddForeignKey(
                name: "FK_Debts_States_stateID",
                table: "Debts",
                column: "stateID",
                principalTable: "States",
                principalColumn: "_id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Debts_States_stateID",
                table: "Debts");

            migrationBuilder.DropIndex(
                name: "IX_Debts_stateID",
                table: "Debts");

            migrationBuilder.DropColumn(
                name: "stateID",
                table: "Debts");

            migrationBuilder.AddColumn<Guid>(
                name: "debtID",
                table: "States",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"));

            migrationBuilder.CreateIndex(
                name: "IX_States_debtID",
                table: "States",
                column: "debtID",
                unique: true);

            migrationBuilder.AddForeignKey(
                name: "FK_States_Debts_debtID",
                table: "States",
                column: "debtID",
                principalTable: "Debts",
                principalColumn: "_id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
