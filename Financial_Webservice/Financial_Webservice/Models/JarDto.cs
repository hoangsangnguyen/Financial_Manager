using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class JarDto
    {
        public Guid _id { get; set; }

        public string type { get; set; }

        public double incomes { get; set; }

        public double posDebts { get; set; }
        public double negWaittingDebts{ get; set; }
        public double negReadyDebts { get; set; }
        public double negDoneDebts { get; set; }

        public double spendings { get; set; }
        public double avaiableAmount{ get; set; }


    }
}
