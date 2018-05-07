﻿// <auto-generated />
using System;
using Financial_Webservice.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage;
using Microsoft.EntityFrameworkCore.Storage.Internal;

namespace Financial_Webservice.Migrations
{
    [DbContext(typeof(FinancialDBContext))]
    [Migration("20180507122813_update_user_avatar")]
    partial class update_user_avatar
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.1.0-preview1-28290")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Financial_Webservice.Entities.Debt", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("amount");

                    b.Property<DateTime>("date");

                    b.Property<string>("detail");

                    b.Property<bool>("isPositive");

                    b.Property<Guid>("jarID");

                    b.Property<string>("origin")
                        .IsRequired();

                    b.Property<Guid>("stateID");

                    b.HasKey("_id");

                    b.HasIndex("jarID");

                    b.HasIndex("stateID");

                    b.ToTable("Debts");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.Image", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<byte[]>("data")
                        .IsRequired();

                    b.Property<string>("fileName")
                        .IsRequired();

                    b.Property<long>("size");

                    b.HasKey("_id");

                    b.ToTable("Images");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.InCome", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("amount");

                    b.Property<DateTime>("date");

                    b.Property<string>("detail");

                    b.Property<Guid>("jarID");

                    b.HasKey("_id");

                    b.HasIndex("jarID");

                    b.ToTable("Incomes");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.Jar", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<Guid>("typeID");

                    b.Property<Guid>("userID");

                    b.HasKey("_id");

                    b.HasIndex("userID");

                    b.ToTable("Jars");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.SpendingDetail", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<double>("amount");

                    b.Property<DateTime>("date");

                    b.Property<string>("detail");

                    b.Property<Guid>("jarID");

                    b.HasKey("_id");

                    b.HasIndex("jarID");

                    b.ToTable("SpendingsDetail");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.State", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("name")
                        .IsRequired()
                        .HasMaxLength(50);

                    b.HasKey("_id");

                    b.ToTable("States");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.Type", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("name")
                        .IsRequired()
                        .HasMaxLength(50);

                    b.Property<double>("percent");

                    b.HasKey("_id");

                    b.ToTable("Types");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.User", b =>
                {
                    b.Property<Guid>("_id")
                        .ValueGeneratedOnAdd();

                    b.Property<string>("avatarUrl");

                    b.Property<string>("email");

                    b.Property<string>("firstName")
                        .IsRequired()
                        .HasMaxLength(50);

                    b.Property<string>("lastName")
                        .IsRequired()
                        .HasMaxLength(50);

                    b.Property<string>("password")
                        .IsRequired();

                    b.Property<string>("phone");

                    b.Property<string>("token");

                    b.Property<string>("userName")
                        .IsRequired()
                        .HasMaxLength(50);

                    b.HasKey("_id");

                    b.HasIndex("userName")
                        .IsUnique();

                    b.ToTable("Users");
                });

            modelBuilder.Entity("Financial_Webservice.Entities.Debt", b =>
                {
                    b.HasOne("Financial_Webservice.Entities.Jar", "jar")
                        .WithMany("debts")
                        .HasForeignKey("jarID")
                        .OnDelete(DeleteBehavior.Cascade);

                    b.HasOne("Financial_Webservice.Entities.State", "state")
                        .WithMany()
                        .HasForeignKey("stateID")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("Financial_Webservice.Entities.InCome", b =>
                {
                    b.HasOne("Financial_Webservice.Entities.Jar", "jar")
                        .WithMany("incomes")
                        .HasForeignKey("jarID")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("Financial_Webservice.Entities.Jar", b =>
                {
                    b.HasOne("Financial_Webservice.Entities.User", "user")
                        .WithMany("Jars")
                        .HasForeignKey("userID")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("Financial_Webservice.Entities.SpendingDetail", b =>
                {
                    b.HasOne("Financial_Webservice.Entities.Jar", "jar")
                        .WithMany("spendings")
                        .HasForeignKey("jarID")
                        .OnDelete(DeleteBehavior.Cascade);
                });
#pragma warning restore 612, 618
        }
    }
}
