using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class FinancialDBContext : DbContext
    {
        public FinancialDBContext(DbContextOptions<FinancialDBContext> options ) : base(options)
        {
            Database.Migrate();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<User>()
                .HasIndex(u => u.userName)
                .IsUnique();
                
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Jar> Jars{ get; set; }
        public DbSet<Type> Types{ get; set; }
        public DbSet<InCome> Incomes { get; set; }
        public DbSet<Debt> Debts{ get; set; }
        public DbSet<SpendingDetail> SpendingsDetail { get; set; }
        public DbSet<State> States { get; set; }
        public DbSet<Image> Images { get; set; }
        public DbSet<Notification> Notifications { get; set; }

    }
}
