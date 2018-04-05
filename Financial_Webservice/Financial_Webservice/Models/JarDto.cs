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

        public double debts { get; set; }

        public double spendings { get; set; }
    }
}
