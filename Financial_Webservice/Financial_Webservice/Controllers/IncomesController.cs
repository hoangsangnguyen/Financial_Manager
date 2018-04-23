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
using Financial_Webservice.Helpers;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
   // [Route("api/users/{userID}/jars/{jarID}/Incomes")]
    public class IncomesController : Controller
    {
        private IFinancialRepository _financialRepository;

        public IncomesController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet("api/users/{userID}/jars/{jarID}/Incomes")]
        public IActionResult GetIncomesForJars([FromHeader] string token, Guid userID, Guid jarID,
            IncomeResourceParameters incomeResourceParameters )
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return BadRequest(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jar not found";
                return BadRequest(result);
            }

            var incomesFromRepo = _financialRepository.GetIncomesForJar(jarID, incomeResourceParameters);
            if (incomesFromRepo == null)
            {
                result.message = "Incomes not found";
                return NotFound(result);
            }

            var incomesToReturn = Mapper.Map<IEnumerable<Models.InComeDto>>(incomesFromRepo);
            result.success = true;
            result.message = "Get incomes succeed";
            result.results = incomesToReturn;
            return Ok(result);
        }

        [HttpGet("api/users/{userID}/jars/{jarID}/Incomes/{id}", Name = "GetIncome")]
        public IActionResult GetIncome([FromHeader] string token, Guid userID, Guid jarID, Guid id)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return BadRequest(result);
            }

            var incomeFromRepo = _financialRepository.GetIncome(jarID, id);
            if (incomeFromRepo == null)
            {
                result.message = "Incomes not found";
                return NotFound(result);
            }

            var incomeToReturn = Mapper.Map<Models.InComeDto>(incomeFromRepo);
            result.success = true;
            result.message = "Get income succeed";
            result.results = incomeToReturn;
            return Ok(result);
        }

        [HttpPost("api/users/{userID}/jars/{jarID}/Incomes")]
        public IActionResult CreateIncomeForJar([FromHeader] string token, Guid userID, Guid jarID,
            [FromBody] IncomeCreationDto inCome) 
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return BadRequest(result);
            }

            var incomeEntity = Mapper.Map<Entities.InCome>(inCome);
            bool isAdded = _financialRepository.AddIncomeForJar(jarID, incomeEntity);
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Created income failed on saving";
                return Ok(result);
            }

            var inComeToReturn = Mapper.Map<Models.InComeDto>(incomeEntity);
            result.success = true;
            result.message = "Create income succeed";
            result.results = inComeToReturn;

            return CreatedAtRoute("GetIncome", new { token, userID, jarID, id = inComeToReturn._id },
                result);

        }

        [HttpPost("api/users/{userID}/Incomes")]
        public IActionResult CreateIncome([FromHeader] string token, Guid userID, [FromBody] IncomeCreationDto inCome)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return BadRequest(result);
            }

            var incomeEntity = Mapper.Map<Entities.InCome>(inCome);
            bool isAdded = _financialRepository.AddIncome(userID, incomeEntity);
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Created income failed on saving";
                return Ok(result);
            }

            result.success = true;
            result.message = "Create inCome succeed.";
            return Ok(result);
        }
    }
}