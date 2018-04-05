using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Financial_Webservice.Migrations
{
    public partial class init_models : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    firstName = table.Column<string>(maxLength: 50, nullable: false),
                    lastName = table.Column<string>(maxLength: 50, nullable: false),
                    email = table.Column<string>(nullable: true),
                    phone = table.Column<string>(nullable: true),
                    userName = table.Column<string>(maxLength: 50, nullable: false),
                    password = table.Column<string>(nullable: false),
                    token = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x._id);
                });

            migrationBuilder.CreateTable(
                name: "Jars",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    userID = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Jars", x => x._id);
                    table.ForeignKey(
                        name: "FK_Jars_Users_userID",
                        column: x => x.userID,
                        principalTable: "Users",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Debts",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    date = table.Column<DateTime>(nullable: false),
                    detail = table.Column<string>(nullable: true),
                    amount = table.Column<double>(nullable: false),
                    origin = table.Column<string>(nullable: false),
                    jarID = table.Column<Guid>(nullable: false),
                    isPositive = table.Column<bool>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Debts", x => x._id);
                    table.ForeignKey(
                        name: "FK_Debts_Jars_jarID",
                        column: x => x.jarID,
                        principalTable: "Jars",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Incomes",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    date = table.Column<DateTime>(nullable: false),
                    detail = table.Column<string>(nullable: true),
                    amount = table.Column<double>(nullable: false),
                    jarID = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Incomes", x => x._id);
                    table.ForeignKey(
                        name: "FK_Incomes_Jars_jarID",
                        column: x => x.jarID,
                        principalTable: "Jars",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "SpendingsDetail",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    date = table.Column<DateTime>(nullable: false),
                    detail = table.Column<string>(nullable: true),
                    amount = table.Column<double>(nullable: false),
                    jarID = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SpendingsDetail", x => x._id);
                    table.ForeignKey(
                        name: "FK_SpendingsDetail_Jars_jarID",
                        column: x => x.jarID,
                        principalTable: "Jars",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Types",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    name = table.Column<string>(maxLength: 50, nullable: false),
                    jarID = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Types", x => x._id);
                    table.ForeignKey(
                        name: "FK_Types_Jars_jarID",
                        column: x => x.jarID,
                        principalTable: "Jars",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "States",
                columns: table => new
                {
                    _id = table.Column<Guid>(nullable: false),
                    name = table.Column<string>(maxLength: 50, nullable: false),
                    debtID = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_States", x => x._id);
                    table.ForeignKey(
                        name: "FK_States_Debts_debtID",
                        column: x => x.debtID,
                        principalTable: "Debts",
                        principalColumn: "_id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Debts_jarID",
                table: "Debts",
                column: "jarID");

            migrationBuilder.CreateIndex(
                name: "IX_Incomes_jarID",
                table: "Incomes",
                column: "jarID");

            migrationBuilder.CreateIndex(
                name: "IX_Jars_userID",
                table: "Jars",
                column: "userID");

            migrationBuilder.CreateIndex(
                name: "IX_SpendingsDetail_jarID",
                table: "SpendingsDetail",
                column: "jarID");

            migrationBuilder.CreateIndex(
                name: "IX_States_debtID",
                table: "States",
                column: "debtID",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Types_jarID",
                table: "Types",
                column: "jarID",
                unique: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Incomes");

            migrationBuilder.DropTable(
                name: "SpendingsDetail");

            migrationBuilder.DropTable(
                name: "States");

            migrationBuilder.DropTable(
                name: "Types");

            migrationBuilder.DropTable(
                name: "Debts");

            migrationBuilder.DropTable(
                name: "Jars");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
