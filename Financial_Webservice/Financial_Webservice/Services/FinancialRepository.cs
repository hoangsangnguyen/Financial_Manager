using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Financial_Webservice.Entities;
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
            return _context.Users.FirstOrDefault(u => u._id == id).token == token;
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
                    GetIncomesForJar(jar._id);
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
                GetIncomesForJar(id);
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

        public bool JarExists(Guid id)
        {
            return _context.Jars.Any(j => j._id == id);
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

        public IEnumerable<InCome> GetIncomesForJar(Guid jarID)
        {
            try
            {
                return _context.Jars.Where(j => j._id == jarID)
                    .SelectMany(i => i.incomes).ToList();
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

    }
}
