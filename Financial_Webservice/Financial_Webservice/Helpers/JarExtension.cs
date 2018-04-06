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


        public static double GetPosDebtAmount(this Jar jar)
        {
            double sum = 0;

            if (jar.debts != null)
            {
                foreach (var debt in jar.debts)
                {
                    if (debt.jarID == jar._id && debt.isPositive)
                        sum += debt.amount;
                }
            }

            return sum;
        }

        public static double GetNegWaittingDebtAmount(this Jar jar, IEnumerable<State> states)
        {
            double sum = 0;

            var waittingStateId = states.Where(s => s.name.ToLowerInvariant() == StateEnum.WATING.getStateDescription().ToLowerInvariant())
                .FirstOrDefault()._id;

            if (jar.debts != null)
            {
                foreach (var debt in jar.debts)
                {
                    if (debt.jarID == jar._id && !debt.isPositive && debt.stateID == waittingStateId)
                        sum += debt.amount;
                }
            }

            return sum;
        }

        public static double GetNegReadyDebtAmount(this Jar jar, IEnumerable<State> states)
        {
            double sum = 0;

            var readyStateId = states.Where(s => s.name.ToLowerInvariant() == StateEnum.READY.getStateDescription().ToLowerInvariant())
                .FirstOrDefault()._id;

            if (jar.debts != null)
            {
                foreach (var debt in jar.debts)
                {
                    if (debt.jarID == jar._id && !debt.isPositive && debt.stateID == readyStateId)
                        sum += debt.amount;
                }
            }

            return sum;
        }

        public static double GetNegDoneDebtAmount(this Jar jar, IEnumerable<State> states)
        {
            double sum = 0;

            var doneStateId = states.Where(s => s.name.ToLowerInvariant() == StateEnum.DONE.getStateDescription().ToLowerInvariant())
                .FirstOrDefault()._id;

            if (jar.debts != null)
            {
                foreach (var debt in jar.debts)
                {
                    if (debt.jarID == jar._id && !debt.isPositive && debt.stateID == doneStateId)
                        sum += debt.amount;
                }
            }

            return sum;
        }

        public static double GetAvaiableAmount(this Jar jar, IEnumerable<State> states)
        {
            return jar.GetIncomeAmount() - jar.GetNegReadyDebtAmount(states) - jar.GetSpendingAmount();
        }

        public static double GetSpendingAmount(this Jar jar)
        {
            double sum = 0;

            if (jar.spendings != null)
            {
                foreach (var spending in jar.spendings)
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
