using Financial_Webservice.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Helpers
{
    public static class IncomeExtension
    {
        public static double GetIncomeAmount(this IEnumerable<InCome> inComes)
        {
            double sum = 0;
            foreach (InCome income in inComes)
            {
                sum += income.amount;
            }

            return sum;
        }

    }
}
