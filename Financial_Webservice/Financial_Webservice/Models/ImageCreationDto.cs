using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class ImageCreationDto
    {
        [Required]
        public string fileName { get; set; }
        [Required]
        public int size { get; set; }
        [Required]
        public byte[] data { get; set; }
    }
}
