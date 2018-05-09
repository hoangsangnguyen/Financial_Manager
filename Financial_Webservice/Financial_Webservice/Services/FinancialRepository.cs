using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Financial_Webservice.Entities;
using Financial_Webservice.Helpers;
using Financial_Webservice.Models;

namespace Financial_Webservice.Services
{
    public class FinancialRepository : IFinancialRepository
    {
        private FinancialDBContext _context;

        public FinancialRepository(FinancialDBContext context)
        {
            _context = context;
        }

        #region user
        public bool AddUser(User user)
        {
            try
            {
                user._id = Guid.NewGuid();
                _context.Users.Add(user);
                return true;
            }
            catch (Exception e)
            {
                Console.Write("Add USER failed : " + e.Message);
                return false;
            }

        }

        public bool DeleteUser(User user)
        {
            try
            {
                _context.Users.Remove(user);
                return true;
            }
            catch (Exception e)
            {
                Console.Write("Delete user failed : " + e.Message);
                return false;
            }
        }

        public User GetUser(Guid id)
        {
            return _context.Users.FirstOrDefault(u => u._id == id);
        }

        public IEnumerable<User> GetUsers()
        {
            try
            {
                return _context.Users.OrderBy(u => u.firstName)
               .ThenBy(u => u.lastName)
               .ToList();

            }
            catch (Exception e)
            {
                Console.Write("GET USER failed : " + e.Message);
                return null;
            }

        }

        public void UpdateUser(User user)
        {
            // no code in this implementation
        }

        public bool UserExists(Guid userID)
        {
            return _context.Users.Any(u => u._id == userID);
        }

        public bool checkAuthenticated(string token, Guid id)
        {
            try
            {
                return _context.Users.FirstOrDefault(u => u._id == id).token == token;
            }
            catch (Exception e)
            {
                Console.Write("Check authenticated failed : " + e.Message);
                return false;
            }

        }

        public User login(string userName, string password)
        {
            try
            {
                var user = _context.Users.Where(u => u.userName == userName && u.password == password).First();
                return user;
            }
            catch (Exception e)
            {
                Console.Write("Login failed : " + e.Message);
                return null;
            }
        }

        #endregion
        public bool Save()
        {
            try
            {
                return (_context.SaveChanges() >= 0);
            }
            catch (Exception e)
            {
                Console.Write("Save data failed : " + e.Message);
                return false;
            }
        }

        #region JARS
        public IEnumerable<Jar> GetJars(Guid userID)
        {
            try
            {
                var jars = _context.Jars.Where(j => j.userID == userID).ToList();
                foreach (var jar in jars)
                {
                    GetIncomesForJar(jar._id, new IncomeResourceParameters());
                    GetSpendings(jar._id, new SpendingResourceParameters());
                    GetDebtForJar(jar._id, new DebtResourceParameters());
                }
                return jars;
            }
            catch (Exception e)
            {
                Console.Write("Get jars failed");
                return null;
            }
        }

        public Jar GetJar(Guid id)
        {
            try
            {
                var jar = _context.Jars.FirstOrDefault(x => x._id == id);
                GetIncomesForJar(id, new IncomeResourceParameters());
                return jar;
            }
            catch (Exception e)
            {
                Console.Write("Get jar failed " + e.Message);
                return null;
            }
        }

        public bool DeleteJar(Jar jar)
        {
            try
            {
                _context.Jars.Remove(jar);
                return true;
            } catch (Exception e)
            {
                Console.Write("Delete jar failed : " + e.Message);
                return false;
            }
        }

        public bool AddJar(Guid userID, Jar jar)
        {
            try
            {
                jar._id = Guid.NewGuid();
                jar.userID = userID;
                _context.Jars.Add(jar);
                return true;
            }
            catch (Exception e)
            {
                Console.Write("Add jar failed : " + e.Message);
                return false;
            }

        }

        public bool JarExists(Guid userID, Guid id)
        {
            return _context.Jars.Any(j => j._id == id && j.userID == userID);
        }


        #endregion

        #region Types
        public IEnumerable<Entities.Type> GetTypes()
        {
            try
            {
                return _context.Types.ToList();
            } catch (Exception e)
            {
                Console.Write("Get types failed : " + e.Message);
                return null;
            } 
        }

        #endregion

        #region Incomes

        public IEnumerable<InCome> GetIncomesForJar(Guid jarID, IncomeResourceParameters incomeResourceParameters)
        {
            try
            {
                var list = _context.Jars.Where(j => j._id == jarID)
                    .SelectMany(i => i.incomes).ToList();
                DateTime? from = incomeResourceParameters.from;
                DateTime? to = incomeResourceParameters.to;

                if (from != null || to != null)
                {
                    if (from == null)
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();
                    if (to == null)
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).ToList();

                    return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();

                }

                return list.OrderBy(s => s.date);
            } catch (Exception e)
            {
                Console.Write("Get incomes failed : " + e.Message);
                return null;
            }
        }

        public InCome GetIncome(Guid jarID, Guid id)
        {
            try
            {
                return _context.Jars.Where(j => j._id == jarID)
                    .SelectMany(j => j.incomes)
                    .FirstOrDefault(i => i._id == id);
            }
            catch (Exception e)
            {
                Console.Write("Get income failed : " + e.Message);
                return null;
            }
        }

        public bool AddIncomeForJar(Guid jarID, InCome income)
        {
            try
            {
                var jar = GetJar(jarID);

                if (jar != null)
                {
                    if (income._id == Guid.Empty)
                    {
                        income._id = Guid.NewGuid();
                    }
                    income.jarID = jarID;
                    _context.Incomes.Add(income);
                    return true;
                }
                return false;
                
            } catch (Exception e)
            {
                Console.Write("Add income failed " + e.Message);
                return false;
            }
        }

        public bool AddIncome(Guid userID, InCome inCome)
        {
            try
            {
                var jars = GetJars(userID);
                var types = GetTypes();

                for (int i = 0; i < 6; i++)
                {
                    var type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.NHU_CAU_THIET_YEU));
                    switch (i)
                    {
                        case 1:
                            type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.TIET_KIEM_DAI_HAN));
                            break;
                        case 2:
                            type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.GIAO_DUC));
                            break;
                        case 3:
                            type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.THU_HUONG));
                            break;
                        case 4:
                            type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.CHO_DI));
                            break;
                        case 5:
                            type = types.FirstOrDefault(t => t.name == TypeEnumExtension.getTypeDescription(TypeEnum.TU_DO_TAI_CHINH));
                            break;
                    }
                    InCome inComeForJar = new InCome();
                    inComeForJar._id = Guid.NewGuid();
                    inComeForJar.amount = inCome.amount * type.percent;
                    inComeForJar.date = inCome.date;
                    inComeForJar.detail = inCome.detail;
                    inComeForJar.jarID = jars.FirstOrDefault(j => j.typeID == type._id)._id;
                    _context.Incomes.Add(inComeForJar);

                }

                return true;

            } catch (Exception e)
            {
                Console.Write("Add income failed : " + e.Message);
                return false;
            }
        }

        public bool DeleteIncome(InCome inCome)
        {
            try
            {
                _context.Incomes.Remove(inCome);
                return true;
            } catch (Exception e)
            {
                Console.Write("Delete income failed " + e.Message);
                return false;
            }
        }

        public void UpdateIncome(InCome inCome)
        {
            // Not implement
        }



        #endregion

        #region states
        public IEnumerable<State> GetStates()
        {
            try
            {
                return _context.States.ToList();
            } catch (Exception e)
            {
                Console.Write("Get states failed : " + e.Message);
                return null;
            }
        }

        #endregion

        #region Spending
        public IEnumerable<SpendingDetail> GetSpendings(Guid jarID, SpendingResourceParameters spendingResourceParameters)
        {
            try
            {
                DateTime? from = spendingResourceParameters.from;
                DateTime? to = spendingResourceParameters.to;
                var list = _context.SpendingsDetail.Where(s => s.jarID == jarID).ToList();

                if (from != null || to != null)
                {
                    if (from == null) 
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();
                    if (to == null)
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).ToList();

                    return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();

                }

                return list;
            }
            catch (Exception e)
            {
                Console.Write($"Get spending for jar {jarID} failed : " + e.Message);
                return null;
            }
        }

        public SpendingDetail GetSpendingByID(Guid id)
        {
            try
            {
                return _context.SpendingsDetail.FirstOrDefault(s => s._id == id);
            }
            catch (Exception e)
            {
                Console.Write($"Get spending by id {id} failed : " + e.Message);
                return null;
            }
        }

        public bool AddSpending(Guid jarID, SpendingDetail spending)
        {
            try
            {
                var incomes = _context.Incomes.Where(i => i.jarID == jarID).Sum(x => x.amount);
                Guid readyStateID = _context.States.FirstOrDefault(s => s.name == StateEnum.READY.getStateDescription())._id;
                var debt = _context.Debts.Where(d => d.jarID == jarID && d.stateID == readyStateID).Sum(d => d.amount);
                if (spending.amount > (incomes - debt))
                    return false;

                if (spending._id == Guid.Empty)
                {
                    spending._id = Guid.NewGuid();
                }
                spending.jarID = jarID;
                _context.SpendingsDetail.Add(spending);
                return true;
            }
            catch (Exception e)
            {
                Console.Write("Create spending failed : " + e.Message);
                return false;
            }
        }

        public bool DeleteSpending(SpendingDetail spending)
        {
            try
            {
                _context.SpendingsDetail.Remove(spending);
                return true;
            } catch (Exception e)
            {
                Console.Write("Delete spending failed : " + e.Message);
                return false;
            }
            
        }

        public bool DeleteSpendingsList(Guid jarID, IEnumerable<Guid> spendingsID)
        {
            try
            {
                var list = GetSpendings(jarID, new SpendingResourceParameters()).Where(x => spendingsID.Contains(x._id)).ToList();
                if (list.Count() == spendingsID.Count())
                {
                    _context.SpendingsDetail.RemoveRange(list);
                    return true;
                }
                return false;
            }
            catch (Exception e)
            {
                Console.Write("Delete spending failed : " + e.Message);
                return false;
            }
        }

        #endregion

        #region Debts
        public IEnumerable<Debt> GetDebtForJar(Guid jarID, DebtResourceParameters debtResourceParameters)
        {
            try
            {
                DateTime? from = debtResourceParameters.from;
                DateTime? to = debtResourceParameters.to;
                var list = _context.Debts.Where(s => s.jarID == jarID).ToList();

                if (from != null || to != null)
                {
                    if (from == null)
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();
                    if (to == null)
                        return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).ToList();

                    return list.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).ToList();

                }

                return list;

                //if (debtResourceParameters.isPositive != null)
                //{
                //    string _positive = debtResourceParameters.isPositive.ToLowerInvariant() ;
                //    if (_positive == "true")
                //        return _context.Debts.Where(d => d.jarID == jarID && d.isPositive).ToList();
                //    if (_positive == "false")
                //        return _context.Debts.Where(d => d.jarID == jarID && ! d.isPositive).ToList();
                //}

                //return _context.Debts.Where(x => x.jarID == jarID).ToList();
            } catch (Exception e)
            {
                Console.Write("Get debt for jar failed : " + e.Message);
                return null;
            }
            
        }

        public Debt GetDebtByID(Guid jarID, Guid id)
        {
            try
            {
                return _context.Debts.FirstOrDefault(x => x.jarID == jarID && x._id == id);
            }
            catch (Exception e)
            {
                Console.Write($"Get debt by id {id} failed : " + e.Message);
                return null;
            }
        }

        public bool AddDebt(Guid jarID, Debt debt)
        {
            try
            {
                if (debt._id == Guid.Empty)
                    debt._id = Guid.NewGuid();

                debt.jarID = jarID;
                _context.Debts.Add(debt);

                return true;
            }
            catch (Exception e)
            {
                Console.Write($"Add debt for jar {jarID} failed : " + e.Message);
                return false;
            }
        }

        public bool UpdateDebt(Guid jarID, Debt debt)
        {
            try
            {
                GetIncomesForJar(jarID, new IncomeResourceParameters());
                GetSpendings(jarID, new SpendingResourceParameters());

                var jar = _context.Jars.FirstOrDefault(j => j._id == jarID);
                var avaiableAmount = jar.GetAvaiableAmount(_context.States);
                Guid doneStateID = _context.States.Where(s => s.name.ToLowerInvariant() == StateEnum.DONE.getStateDescription().ToLowerInvariant())
                    .First()._id;

                Guid readyStateID = _context.States.Where(s => s.name.ToLowerInvariant() == StateEnum.READY.getStateDescription().ToLowerInvariant())
                   .First()._id;
                if (debt.stateID == doneStateID || debt.stateID == readyStateID)
                {
                    if (debt.amount > avaiableAmount)
                        return false;
                }

                if (debt.stateID == doneStateID)
                {
                    // change done debt to spending
                    SpendingDetail spending = new SpendingDetail()
                    {
                        _id = Guid.NewGuid(),
                        date = DateTime.UtcNow,
                        detail = "From debt : " + debt.detail,
                        amount = debt.amount,
                        jarID = jarID
                    };

                    _context.SpendingsDetail.Add(spending);

                }

                return true;
            } catch (Exception e)
            {
                Console.Write("Update debt failed : " + e.Message);
                return false;
            }

        }

        public bool DeleteDebt(Guid jarID, Debt debt)
        {
            try
            {
                if (debt.jarID == jarID)
                {
                    _context.Debts.Remove(debt);
                    return true;
                }

                return false;
            }
            catch (Exception e)
            {
                Console.Write($"Delete debt {debt._id} failed : " + e.Message);
                return false;
            }
        }


        #endregion
        #region Images
        public Image GetImageById(Guid id)
        {
            try
            {
                return _context.Images.FirstOrDefault(x => x._id == id);
            }
            catch (Exception e)
            {
                Console.Write($"Get image {id} failed : " + e.Message);
                return null;
            }
        }

        public bool SaveImage(Image image)
        {
            try
            {
                image._id = Guid.NewGuid();
                _context.Images.Add(image);
                return true;
            }
            catch (Exception e)
            {
                Console.Write($"Save image {image._id} failed : " + e.Message);
                return false;
            }
        }

        public bool DeleteImage(Image image)
        {
            try
            {
                _context.Images.Remove(image);
                return true;
            }
            catch (Exception e)
            {
                Console.Write($"Delete image {image._id} failed : " + e.Message);
                return false;
            }
        }


        #endregion

        #region Statistics
        public StatisticDto GetStatisticsByMonths(Guid userID, PeriodResourceParameters period)
        {
            try
            {
                var jarIDList = _context.Jars.Where(j => j.userID == userID).Select(j => j._id).ToList();

                DateTime? from = period.from;
                DateTime? to = period.to;
                var debts = _context.Debts.Where(d => jarIDList.Any(x => x == d.jarID)).ToList();
                var incomes = _context.Incomes.Where(d => jarIDList.Any(x => x == d.jarID)).ToList();
                var spendings = _context.SpendingsDetail.Where(d => jarIDList.Any(x => x == d.jarID)).ToList();

                if (from != null || to != null)
                {
                    if (from == null)
                    {
                        debts = debts.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                        incomes = incomes.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                        spendings = spendings.Where(s => DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                    }
                    else if (to == null)
                    {
                        debts = debts.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).OrderBy(s => s.date).ToList();
                        incomes = incomes.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).OrderBy(s => s.date).ToList();
                        spendings = spendings.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0).OrderBy(s => s.date).ToList();
                    }
                    else
                    {
                        debts = debts.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                        incomes = incomes.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                        spendings = spendings.Where(s => DateTime.Compare(s.date, (DateTime)from) >= 0 && DateTime.Compare(s.date, (DateTime)to) <= 0).OrderBy(s => s.date).ToList();
                    }
                }

                StatisticDto result = new StatisticDto();
                result.incomes = GetIncomesByMonths(incomes);
                result.spendings = GetSpendingsByMonths(spendings);
                result.debts = GetDebtByMonths(debts);
                return result;

            } catch (Exception e)
            {
                Console.Write($"Statistics failed : " + e.Message);
                return null;
            }
        }

        private List<double> GetDebtByMonths(List<Debt> debts)
        {
            List<double> list = new List<double>();
            for (int i = 0; i < 12; i++)
            {
                list.Add(0);
            }

            foreach(Debt debt in debts)
            {
                int month = debt.date.Month;
                list[month-1] += debt.amount;
            }

            return list;
        }

        private List<double> GetIncomesByMonths(List<InCome> incomes)
        {
            List<double> list = new List<double>();
            for (int i = 0; i < 12; i++)
            {
                list.Add(0);
            }

            foreach (InCome income in incomes)
            {
                int month = income.date.Month;
                list[month-1] += income.amount;
            }

            return list;
        }

        private List<double> GetSpendingsByMonths(List<SpendingDetail> spendings)
        {
            List<double> list = new List<double>();
            for (int i = 0; i < 12; i++)
            {
                list.Add(0);
            }

            foreach (SpendingDetail spending in spendings)
            {
                int month = spending.date.Month;
                list[month-1] += spending.amount;
            }

            return list;
        }


        #endregion
        #region Notification
        public List<Notification> GetAllNotificationsForUser(Guid userID)
        {
            try
            {
                return _context.Notifications.Where(n => n.userID == userID).OrderBy(n => n.date).ToList();
            }
            catch (Exception e)
            {
                Console.Write($"Get notifications for user {userID} failed : " + e.Message);
                return null;
            }
        }

        public List<Notification> GetNextNotificationsForUser(Guid userID)
        {
            try
            {
                return _context.Notifications.Where(n => n.userID == userID && 
                                                    n.date >= DateTime.Now).OrderBy(n => n.date).ToList();
            }
            catch (Exception e)
            {
                Console.Write($"Get next notifications for user {userID} failed : " + e.Message);
                return null;
            }
        }

        public bool CreateNotification(Guid userID, Notification notification)
        {
            try
            {
                notification._id = Guid.NewGuid();
                notification.userID = userID;
                _context.Notifications.Add(notification);
                return true;
            }
            catch (Exception e)
            {
                Console.Write($"Create notification for user {userID} failed : " + e.Message);
                return false;
            }
        }

        public bool DeleteNotification(Notification notification)
        {
            try
            {
                _context.Notifications.Remove(notification);
                return true;
            }
            catch (Exception e)
            {
                Console.Write($"Delete notification {notification._id} failed : " + e.Message);
                return false;
            }
        }

        public bool UpdateNotification(Notification notification)
        {
            // not implement
            return true;
        }

        public Notification GetNotificationById(Guid id)
        {
            try
            {
               return _context.Notifications.FirstOrDefault(n => n._id == id);
            }
            catch (Exception e)
            {
                Console.Write($"Get notification by ID {id} failed : " + e.Message);
                return null;
            }

        }

        #endregion
    }
}
