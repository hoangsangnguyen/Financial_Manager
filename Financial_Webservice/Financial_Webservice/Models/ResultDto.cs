using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class ResultDto
    {
        public bool success { get; set; } = false;
        public string message { get; set; }
        public Object results { get; set; }
    }
}
