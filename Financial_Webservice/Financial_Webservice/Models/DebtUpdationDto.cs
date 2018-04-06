using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class DebtUpdationDto
    {
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime date { get; set; }

        public string detail { get; set; }
        public double amount { get; set; }

        public string origin { get; set; }

        public string state { get; set; }

        public bool isPositive { get; set; }
    }
}
