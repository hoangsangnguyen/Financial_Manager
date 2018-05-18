using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class StatisticDto
    {
        public List<double> incomes { get; set; }
        public List<double> spendings { get; set; }

        public List<double> debts { get; set; }

    }
}
