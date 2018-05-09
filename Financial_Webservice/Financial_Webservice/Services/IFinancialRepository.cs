using Financial_Webservice.Entities;
using Financial_Webservice.Helpers;
using Financial_Webservice.Models;
using System;
using System.Collections.Generic;

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
        bool JarExists(Guid userID, Guid id);

        #endregion

        #region type
        IEnumerable<Entities.Type> GetTypes();
        #endregion

        #region Incomes
        IEnumerable<InCome> GetIncomesForJar( Guid jarID, IncomeResourceParameters incomeResourceParameters);
        InCome GetIncome( Guid jarID, Guid id);
        bool AddIncomeForJar(Guid jarID, InCome income);
        bool AddIncome(Guid userID, InCome inCome);
        bool DeleteIncome(InCome inCome);
        void UpdateIncome(InCome inCome);

        #endregion
        #region state
        IEnumerable<State> GetStates();
        #endregion

        #region Spending
        IEnumerable<SpendingDetail> GetSpendings(Guid jarID, SpendingResourceParameters spendingResourceParameters );
        SpendingDetail GetSpendingByID(Guid id);
        bool AddSpending(Guid jarID, SpendingDetail spending);
        bool DeleteSpending(SpendingDetail spending);
        bool DeleteSpendingsList(Guid jarID, IEnumerable<Guid> spendingsID);
        #endregion

        #region Debt
        IEnumerable<Debt> GetDebtForJar(Guid jarID, DebtResourceParameters debtResourceParameters);
        Debt GetDebtByID(Guid jarID, Guid id);
        bool AddDebt(Guid jarID, Debt debt);
        bool UpdateDebt(Guid jarID, Debt debt);
        bool DeleteDebt(Guid jarID, Debt debt);
        #endregion

        #region Images
        Image GetImageById(Guid id);
        bool SaveImage(Image image);
        bool DeleteImage(Image image);

        #endregion

        #region Statistics
        StatisticDto GetStatisticsByMonths(Guid userID, PeriodResourceParameters period);
        #endregion

        #region Notification
        List<Notification> GetAllNotificationsForUser(Guid userID);
        List<Notification> GetNextNotificationsForUser(Guid userID);
        Notification GetNotificationById(Guid id);
        bool CreateNotification(Guid userID, Notification notification);
        bool DeleteNotification(Notification notification);
        bool UpdateNotification(Notification notification);
        #endregion
        bool Save();
    }
}
