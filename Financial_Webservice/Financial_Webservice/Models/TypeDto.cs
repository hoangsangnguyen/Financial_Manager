using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class TypeDto
    {
        public Guid _id { get; set; }
    
        public string name { get; set; }
        public double percent { get; set; }

    }
}
