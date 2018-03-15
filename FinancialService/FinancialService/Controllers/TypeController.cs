using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class TypeController : ApiController
    {
        [HttpGet]
        public ResultModel getTypeLists()
        {
            DbFinancialDataContext db = new DbFinancialDataContext();
            ResultModel result = new ResultModel();

            try
            {
                result.results = db.Types.ToList();
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
