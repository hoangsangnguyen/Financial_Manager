using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class DebtController : ApiController
    {
        DbFinancialDataContext db = new DbFinancialDataContext();
        [HttpGet]
        public ResultModel getAllDebt([FromUri] int jarID, [FromUri] int userID)
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
                userName = db.Users.Where(x => x.ID == userID).First().userName;
            }
            catch
            {
                result.success = false;
                result.message = "Create debt failed";
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
                    var debtLists = db.Debts.Where(x => x.userID == userID && x.jarID == jarID).ToList();
                    result.results = debtLists;
                    result.success = true;
                    result.message = "Get all debts success.";
                }
                catch
                {
                    result.success = false;
                    result.message = "Get debts failed";
                }

            }
            return result;
        }

        [HttpPost]
        public ResultModel createDebt([FromBody] Debt debt)
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
                userName = db.Users.Where(x => x.ID == debt.userID).First().userName;

            }
            catch
            {
                result.success = false;
                result.message = "Create debt failed";
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
                    var response = db.sp_InsertDebt(debt.date, debt.detail, debt.amount,
                        debt.origin, debt.stateID, debt.jarID, debt.userID, debt.isPositive);
                    if (response == 1)
                    {
                        result.success = false;
                        result.message = "Debt amount is over amount available !";
                    }
                    else
                    {
                        db.Debts.InsertOnSubmit(debt);

                        var jarLists = db.JARs.Where(x => x.userID == debt.userID).ToList();
                        result.results = jarLists;
                        result.success = true;

                        db.SubmitChanges();
                    }

                }
                catch { result.success = false; }

            }
            return result;
        }

        [HttpPut]
        public ResultModel updateDebt([FromBody] Debt debt)
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
                userName = db.Users.Where(x => x.ID == debt.userID).First().userName;

            }
            catch
            {
                result.success = false;
                result.message = "Update debt failed";
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
                    var response = db.sp_UpdateDebt(debt.ID, debt.date, debt.detail, debt.amount,
                        debt.origin, debt.stateID, debt.jarID, debt.userID, debt.isPositive);
                    if (response == 0)
                    {
                        result.success = true;
                        result.message = "Update debt success !";
                        result.results = db.Debts.Where(x => x.ID == debt.ID);
                    } else
                    {
                        result.success = false;
                        result.message = "Update debt failed";
                    }

                }
                catch { result.success = false; }

            }
            return result;
        }

        [HttpDelete]
        public ResultModel deleteDebt([FromBody] Debt debt)
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
                var userID = db.Debts.Where(x => x.ID == debt.ID).First().userID;
                userName = db.Users.Where(x => x.ID == userID).First().userName;

            } catch
            {
                result.success = false;
                result.message = "Delete debt failed";
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
                    var response = db.sp_DeleteDebt(debt.ID);
                    if (response == 0)
                    {
                        result.success = true;
                        result.message = "Delete debt success !";
                    }
                    else
                    {
                        result.success = false;
                        result.message = "Delete debt failed";
                    }

                }
                catch { result.success = false; }

            }
            return result;
        }

        private bool checkAuthenticated(string token, string userName)
        {
            string tokenDB = db.Users.FirstOrDefault(x => x.userName == userName).token;
            return token.Equals(tokenDB);
        }
    }
}
