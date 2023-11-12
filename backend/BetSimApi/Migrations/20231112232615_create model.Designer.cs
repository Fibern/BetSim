﻿// <auto-generated />
using System;
using BetSimApi;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace BetSimApi.Migrations
{
    [DbContext(typeof(DbMainContext))]
    [Migration("20231112232615_create model")]
    partial class createmodel
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.13")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            NpgsqlModelBuilderExtensions.UseIdentityByDefaultColumns(modelBuilder);

            modelBuilder.Entity("BetSimApi.Model.Bet", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int?>("CouponId")
                        .HasColumnType("integer");

                    b.Property<int>("OffertIdId")
                        .HasColumnType("integer");

                    b.Property<int>("PredictedWinnerId")
                        .HasColumnType("integer");

                    b.Property<int>("status")
                        .HasColumnType("integer");

                    b.HasKey("Id");

                    b.HasIndex("CouponId");

                    b.HasIndex("OffertIdId");

                    b.HasIndex("PredictedWinnerId");

                    b.ToTable("Bet");
                });

            modelBuilder.Entity("BetSimApi.Model.Coupon", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int>("UserId")
                        .HasColumnType("integer");

                    b.Property<double>("Value")
                        .HasColumnType("double precision");

                    b.HasKey("Id");

                    b.HasIndex("UserId");

                    b.ToTable("Coupon");
                });

            modelBuilder.Entity("BetSimApi.Model.Event", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Icon")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Title")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("Event");
                });

            modelBuilder.Entity("BetSimApi.Model.Odd", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<double>("OddValue")
                        .HasColumnType("double precision");

                    b.Property<int?>("OffertId")
                        .HasColumnType("integer");

                    b.Property<string>("PlayerName")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.HasIndex("OffertId");

                    b.ToTable("Odd");
                });

            modelBuilder.Entity("BetSimApi.Model.Offert", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Date")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<int>("EventId")
                        .HasColumnType("integer");

                    b.Property<string>("Score")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Title")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<int>("Type")
                        .HasColumnType("integer");

                    b.Property<string>("Winner")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.HasIndex("EventId");

                    b.ToTable("Offert");
                });

            modelBuilder.Entity("BetSimApi.Model.User", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Login")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Password")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<byte[]>("PasswordHash")
                        .IsRequired()
                        .HasColumnType("bytea");

                    b.Property<byte[]>("PasswordSalt")
                        .IsRequired()
                        .HasColumnType("bytea");

                    b.Property<double>("Points")
                        .HasColumnType("double precision");

                    b.Property<string>("Role")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("User");
                });

            modelBuilder.Entity("EventUser", b =>
                {
                    b.Property<int>("AdministratorsId")
                        .HasColumnType("integer");

                    b.Property<int>("EventsCreatedId")
                        .HasColumnType("integer");

                    b.HasKey("AdministratorsId", "EventsCreatedId");

                    b.HasIndex("EventsCreatedId");

                    b.ToTable("EventUser");
                });

            modelBuilder.Entity("BetSimApi.Model.Bet", b =>
                {
                    b.HasOne("BetSimApi.Model.Coupon", "Coupon")
                        .WithMany("Bets")
                        .HasForeignKey("CouponId");

                    b.HasOne("BetSimApi.Model.Offert", "OffertId")
                        .WithMany()
                        .HasForeignKey("OffertIdId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("BetSimApi.Model.Odd", "PredictedWinner")
                        .WithMany()
                        .HasForeignKey("PredictedWinnerId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Coupon");

                    b.Navigation("OffertId");

                    b.Navigation("PredictedWinner");
                });

            modelBuilder.Entity("BetSimApi.Model.Coupon", b =>
                {
                    b.HasOne("BetSimApi.Model.User", "User")
                        .WithMany("Coupons")
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("User");
                });

            modelBuilder.Entity("BetSimApi.Model.Odd", b =>
                {
                    b.HasOne("BetSimApi.Model.Offert", "Offert")
                        .WithMany("Odds")
                        .HasForeignKey("OffertId");

                    b.Navigation("Offert");
                });

            modelBuilder.Entity("BetSimApi.Model.Offert", b =>
                {
                    b.HasOne("BetSimApi.Model.Event", "Event")
                        .WithMany("Offerts")
                        .HasForeignKey("EventId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Event");
                });

            modelBuilder.Entity("EventUser", b =>
                {
                    b.HasOne("BetSimApi.Model.User", null)
                        .WithMany()
                        .HasForeignKey("AdministratorsId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("BetSimApi.Model.Event", null)
                        .WithMany()
                        .HasForeignKey("EventsCreatedId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("BetSimApi.Model.Coupon", b =>
                {
                    b.Navigation("Bets");
                });

            modelBuilder.Entity("BetSimApi.Model.Event", b =>
                {
                    b.Navigation("Offerts");
                });

            modelBuilder.Entity("BetSimApi.Model.Offert", b =>
                {
                    b.Navigation("Odds");
                });

            modelBuilder.Entity("BetSimApi.Model.User", b =>
                {
                    b.Navigation("Coupons");
                });
#pragma warning restore 612, 618
        }
    }
}
