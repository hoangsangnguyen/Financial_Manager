using Financial_Webservice.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Services
{
    public interface IFinancialRepository
    {
        #region user
        IEnumerable<User> GetUsers();
        User GetUser(Guid id);
        bool AddUser(User user);
        void UpdateUser(User user);
        bool DeleteUser(User user);
        bool UserExists(Guid userID);
        bool checkAuthenticated(string token, Guid id);
        User login(string userName, string password);

        #endregion

        #region JARS
        IEnumerable<Jar> GetJars(Guid userID);
        Jar GetJar(Guid id);
        bool DeleteJar(Jar jar);
        bool AddJar(Guid userID, Jar jar);
        bool JarExists(Guid id);

        #endregion

        #region type
        IEnumerable<Entities.Type> GetTypes();
        #endregion

        #region Incomes
        IEnumerable<InCome> GetIncomesForJar( Guid jarID);
        InCome GetIncome( Guid jarID, Guid id);
        bool AddIncomeForJar(Guid jarID, InCome income);
        bool AddIncome(Guid userID, InCome inCome);
        bool DeleteIncome(InCome inCome);
        void UpdateIncome(InCome inCome);

        #endregion
        bool Save();
    }
}
