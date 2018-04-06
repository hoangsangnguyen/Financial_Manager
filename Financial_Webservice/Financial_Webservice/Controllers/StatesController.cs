using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Financial_Webservice.Services;
using Financial_Webservice.Entities;
using Financial_Webservice.Models;
using AutoMapper;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
    [Route("api/States")]
    public class StatesController : Controller
    {
        private IFinancialRepository _financialRepository;
        public StatesController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet]
        public IActionResult GetStates()
        {
            ResultDto result = new ResultDto();
            var statesFromRepo = _financialRepository.GetStates();
            if (statesFromRepo == null)
            {
                result.message = "Get states failed";
                return NotFound(result);
            }

            var statesToReturn = Mapper.Map<IEnumerable<StateDto>>(statesFromRepo);
            result.success = true;
            result.message = "Get states succeed";
            result.results = statesToReturn;
            return Ok(result);
        }
    }
}