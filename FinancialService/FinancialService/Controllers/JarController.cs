using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class JarController : ApiController
    {
        DbFinancialDataContext db = new DbFinancialDataContext();

        [HttpPost]
        public ResultModel getJars([FromBody] string userName)
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

                    var userId = db.Users.Where(x => x.userName.Equals(userName)).First().ID;
                    var jarLists = db.JARs.Where(x => x.userID == userId).ToList();

                    result.results = jarLists;
                    result.success = true;

                } catch { result.success = false; }
               
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
