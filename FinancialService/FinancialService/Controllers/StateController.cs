using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class StateController : ApiController
    {
        [HttpGet]
        public ResultModel getStateLists()
        {
            DbFinancialDataContext db = new DbFinancialDataContext();
            ResultModel result = new ResultModel();

            try
            {
                result.results = db.States.ToList();
                result.success = true;
            }
            catch
            {
                result.success = false;
            }

            return result;
        }
    }
}
