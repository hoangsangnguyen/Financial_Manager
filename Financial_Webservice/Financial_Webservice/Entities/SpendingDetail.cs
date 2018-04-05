using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class SpendingDetail
    {
        [Key]
        public Guid _id { get; set; }

        [Required]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime date { get; set; }

        public string detail { get; set; }
        [Required]
        public double amount { get; set; }

        [ForeignKey("jarID")]
        public Jar jar { get; set; }
        public Guid jarID { get; set; }

    }
}
