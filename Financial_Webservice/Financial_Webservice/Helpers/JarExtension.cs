using Financial_Webservice.Entities;
using Financial_Webservice.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Financial_Webservice.Services;

namespace Financial_Webservice.Helpers
{
    public static class JarExtension
    {
        public static double GetIncomeAmount(this Jar jar)
        {
            double sum = 0;

            if (jar.incomes != null)
            {
                foreach (var income in jar.incomes)
                {
                    sum += income.amount;
                }
            }

            return sum;
        }


        public static double GetDebtAmount(this Jar jar, IEnumerable<Debt> debts)
        {
            double sum = 0;

            if (debts != null)
            {
                foreach (var debt in debts)
                {
                    if (debt.jarID == jar._id)
                        sum += debt.amount;
                }
            }

            return sum;
        }

        public static double GetSpendingAmount(this Jar jar, IEnumerable<SpendingDetail> spendings)
        {
            double sum = 0;

            if (spendings != null)
            {
                foreach (var spending in spendings)
                {
                    if (spending.jarID == jar._id)
                        sum += spending.amount;
                }
            }

            return sum;
        }
        public static Entities.Type GetTypeFromName(this JarCreationDto jar, IEnumerable<Entities.Type> types)
        {
            var type = types.Where(t => t.name.ToLowerInvariant() == jar.type.ToLowerInvariant()).First();
            return type;
        }

        public static Guid GetTypeIDFromName(this JarCreationDto jar, IEnumerable<Entities.Type> types)
        {
            var type = types.Where(t => t.name.ToLowerInvariant() == jar.type.ToLowerInvariant()).First();
            return type._id;
        }

        public static string GetTypeNameFromID(this Jar jar, IEnumerable<Entities.Type> types)
        {
            var type = types.Where(t => t._id == jar.typeID).First();
            return type.name;
        }

    }
}
