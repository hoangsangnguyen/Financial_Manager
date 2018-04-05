using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class Type
    {
        [Key]
        public Guid _id { get; set; }
        [Required]
        [MaxLength(50)]
        public string name { get; set; }
        [Required]
        public double percent { get; set; }

    }
}
