using Financial_Webservice.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class Jar
    {
        [Key]
        public Guid _id { get; set; }

        [ForeignKey("typeID")]
        [NotMapped]
        public virtual Type type { get; set; }
        [Required]
        public Guid typeID { get; set; }

        public ICollection<InCome> incomes { get; set; }

        public ICollection<Debt> debts { get; set; }

        public ICollection<SpendingDetail> spendings { get; set; }

        [ForeignKey("userID")]
        public User user { get; set; }
        public Guid userID { get; set; }
    }
}
