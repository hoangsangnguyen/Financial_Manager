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
    [Route("api/Types")]
    public class TypesController : Controller
    {
        private IFinancialRepository _financialRepository;

        public TypesController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet]
        public IActionResult GetTypes()
        {
            ResultDto result = new ResultDto();
            var typesFromRepo = _financialRepository.GetTypes();
            if (typesFromRepo == null)
            {
                result.message = "Not found";
                return NotFound(result);
            }
            var typesToReturn = Mapper.Map<IEnumerable<Models.TypeDto>>(typesFromRepo);
            result.success = true;
            result.message = "Get types succeed";
            result.results = typesToReturn;
            return Ok(result);
        }
    }
}