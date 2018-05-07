using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class Image
    {
        [Key]
        public Guid _id { get; set; }
        [Required]
        public string fileName { get; set; }
        [Required]
        public long size { get; set; }
        [Required]
        public byte[] data { get; set; }

    }
}
