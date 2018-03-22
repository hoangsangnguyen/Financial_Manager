using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class JarController : ApiController
    {
        DbFinancialDataContext db = new DbFinancialDataContext();

        [HttpGet]
        public ResultModel getJars([FromUri] string userName)
        {
            System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
            string token = string.Empty;
            if (headers.Contains("token"))
            {
                token = headers.GetValues("token").First();
            }
           
            ResultModel result = new ResultModel();
           
            if (!checkAuthenticated(token, userName))
            {
                result.success = false;
            } else
            {
                try
                {
                    if (db.sp_checkUserHasJars(userName) == 1) // user hasn't jar lists
                    {
                        db.sp_CreateJarLists(userName);
                    }

                    var userId = db.Users.Where(x => x.userName.Equals(userName)).First()._id;
                    var jarLists = db.JARs.Where(x => x.userID == userId).ToList();

                    result.results = jarLists;
                    result.success = true;

                } catch { result.success = false; }
               
            }
            return result;
        }

        [HttpGet]
        public ResultModel getJarByID([FromUri] string userName, [FromUri] int typeId)
        {
            System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
            string token = string.Empty;
            if (headers.Contains("token"))
            {
                token = headers.GetValues("token").First();
            }

            ResultModel result = new ResultModel();

            if (!checkAuthenticated(token, userName))
            {
                result.success = false;
            }
            else
            {
                try
                {
                    if (db.sp_checkUserHasJars(userName) == 1) // user hasn't jar lists
                    {
                        db.sp_CreateJarLists(userName);
                    }

                    var userId = db.Users.Where(x => x.userName.Equals(userName)).First()._id;
                    var jarLists = db.JARs.Where(x => x.userID == userId && x.typeID == typeId).ToList();

                    result.results = jarLists;
                    result.success = true;

                }
                catch { result.success = false; }

            }
            return result;
        }

        [HttpPut]
        public ResultModel updateJars([FromBody] Income inCome)
        {
            System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
            string token = string.Empty;
            if (headers.Contains("token"))
            {
                token = headers.GetValues("token").First();
            }

            ResultModel result = new ResultModel();
            var userName = "";
            try
            {
                userName = db.Users.Where(x => x._id == inCome.userID).First().userName;
            }
            catch
            {
                result.success = false;
                result.message = "Update Jars failed";
                return result;
            }

            if (!checkAuthenticated(token, userName))
            {
                result.success = false;
            }
            else
            {
                try
                {
                    db.Incomes.InsertOnSubmit(inCome);
                    db.sp_UpdateIncomeAmount(inCome.date, inCome.detail, inCome.amount, inCome.jarID, inCome.userID);
                    var jarLists = db.JARs.Where(x => x.userID == inCome.userID).ToList();
                    result.results = jarLists;
                    result.success = true;

                    db.SubmitChanges();
                }
                catch { result.success = false; }

            }
            return result;
        }

        [HttpGet]
        public ResultModel getJarIncome([FromUri] int jarID, [FromUri] int userID)
        {
            System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
            string token = string.Empty;
            if (headers.Contains("token"))
            {
                token = headers.GetValues("token").First();
            }

            ResultModel result = new ResultModel();
            var userName = "";
            try
            {
                userName = db.Users.Where(x => x._id == userID).First().userName;
            }
            catch
            {
                result.success = false;
                result.message = "Get Jar income failed";
                return result;
            }

            if (!checkAuthenticated(token, userName))
            {
                result.success = false;
            }
            else
            {
                try
                {
                    var incomeLists = db.Incomes.Where(x => x.userID == userID && x.jarID == jarID).ToList();
                    result.results = incomeLists;
                    result.success = true;

                }
                catch { result.success = false; }

            }
            return result;
        }

        private bool checkAuthenticated (string token, string userName)
        {
            string tokenDB = db.Users.FirstOrDefault(x => x.userName == userName).token;
            return token.Equals(tokenDB);
        }
    }
}
