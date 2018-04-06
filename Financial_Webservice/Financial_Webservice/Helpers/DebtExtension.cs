using Financial_Webservice.Entities;
using Financial_Webservice.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Helpers
{
    public static class DebtExtension
    {
        public static Guid GetStateIdFromName (this DebtCreationDto debt, IEnumerable<State> states)
        {
            var state = states.Where(t => t.name.ToLowerInvariant() == debt.state.ToLowerInvariant()).First();
            return state._id;
        }

        public static Guid GetStateIdFromName(this DebtUpdationDto debt, IEnumerable<State> states)
        {
            var state = states.Where(t => t.name.ToLowerInvariant() == debt.state.ToLowerInvariant()).First();
            return state._id;
        }

        public static string GetStateNameFromID(this Debt debt, IEnumerable<Entities.State> states)
        {
            var state = states.Where(t => t._id == debt.stateID).First();
            return state.name;
        }
    }
}
