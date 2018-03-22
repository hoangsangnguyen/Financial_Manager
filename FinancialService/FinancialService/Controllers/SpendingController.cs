using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class SpendingController : ApiController
    {
        DbFinancialDataContext db = new DbFinancialDataContext();

        [HttpGet]
        public ResultModel getAllSpending([FromUri] int jarID, [FromUri] int userID)
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
                result.message = "Get Jars spending failed";
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
                    List<SpendingDetail> spendingList = db.SpendingDetails.Where(x => x.jarID == jarID && x.userID == userID).ToList();
                    result.results = spendingList;
                    result.success = true;
                    if (spendingList.Any())
                    {
                        result.message = "Get data success.";
                    } else
                    {
                        result.message = "No Data";
                    }
                }
                catch { result.success = false; }

            }
            return result;
        }

        [HttpPost]
        public ResultModel createSpending([FromBody] SpendingDetail spending)
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
                userName = db.Users.Where(x => x._id == spending.userID).First().userName;
            }
            catch
            {
                result.success = false;
                result.message = "Create spending failed";
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
                    var response = db.sp_CreateSpending(spending.date, spending.detail, spending.amount, spending.jarID, spending.userID);
                    if (response == 1)
                    {
                        result.success = false;
                        result.message = "The spending is over the rest of this jar !";

                    } else
                    {
                        result.success = true;
                        result.message = "Success";
                        var jarLists = db.JARs.Where(x => x.userID == spending.userID).ToList();
                        result.results = jarLists;
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
