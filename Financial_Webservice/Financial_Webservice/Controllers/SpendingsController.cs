using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Financial_Webservice.Services;
using Financial_Webservice.Entities;
using Financial_Webservice.Models;
using Financial_Webservice.Helpers;
using AutoMapper;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
    [Route("api/users/{userID}/jars/{jarID}/Spendings")]
    public class SpendingsController : Controller
    {
        private IFinancialRepository _financialRepository;

        public SpendingsController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet]
        public IActionResult GetSpendingsForJar([FromHeader] string token, Guid userID, Guid jarID,
            SpendingResourceParameters spendingResourceParameters)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return NotFound(result);
            }
            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jar not found";
                return NotFound(result);
            }

            var spendingsFromRepo = _financialRepository.GetSpendings(jarID, spendingResourceParameters);
            var spendingsToReturn = Mapper.Map<IEnumerable<SpendingDetailDto>>(spendingsFromRepo);

            result.success = true;
            result.message = "Get spendings for jar succeed";
            result.results = spendingsToReturn;
            return Ok(result);

        }

        [HttpGet("{id}", Name = "GetSpendingByID")]
        public IActionResult GetSpendingByID([FromHeader] string token, Guid userID, Guid jarID, Guid id)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return NotFound(result);
            }
            if (!_financialRepository.JarExists(userID ,jarID))
            {
                result.message = "Jar not found";
                return NotFound(result);
            }

            var spendingFromRepo = _financialRepository.GetSpendingByID(id);
            if (spendingFromRepo == null)
            {
                result.message = $"Spending {id} not found";
                return Ok(result);
            }

            var spendingToReturn = Mapper.Map<SpendingDetailDto>(spendingFromRepo);
            result.success = true;
            result.message = "Get Spending succeed";
            result.results = spendingToReturn;
            return Ok(result);
        }

        [HttpPost]
        public IActionResult CreateSpending([FromHeader] string token, Guid userID, Guid jarID, [FromBody] SpendingCreationDto spending )
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return NotFound(result);
            }

            if (spending == null)
            {
                result.message = "Spending is null";
                return BadRequest(result);
            }

            var spendingEntity = Mapper.Map<SpendingDetail>(spending);
            bool isAdded = _financialRepository.AddSpending(jarID, spendingEntity);
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Create spending failed on saving";
                return Ok(result);
            }

            var spendingToReturn = Mapper.Map<SpendingDetailDto>(spendingEntity);
            result.success = true;
            result.message = "Create spending succeed";
            result.results = spendingToReturn;

            return CreatedAtRoute("GetSpendingByID", new { id = spendingEntity._id }, result);
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteSpending([FromHeader] string token, Guid userID, Guid jarID, Guid id)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return NotFound(result);
            }
            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jar not found";
                return NotFound(result);
            }

            var spendingFromRepo = _financialRepository.GetSpendingByID(id);
            if (spendingFromRepo == null)
            {
                result.message = $"Spending {id} not found";
                return NotFound(result);
            }

            bool isDelete = _financialRepository.DeleteSpending(spendingFromRepo);
            if (!isDelete || !_financialRepository.Save())
            {
                result.message = "Delete spending failed on saving";
                return Ok(result);
            }

            result.success = true;
            result.message = "Delete spending succeed";
            return Ok(result);
        }

        [HttpDelete]
        public IActionResult DeleteSpendingList([FromHeader] string token, Guid userID, Guid jarID, [FromBody] SpendingDeletionDto spendingsID)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            if (!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return NotFound(result);
            }
            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jar not found";
                return NotFound(result);
            }
            if (spendingsID == null || spendingsID._ids.Count() == 0)
            {
                result.message = "Id lists is null or empty";
                return BadRequest(result);
            }

            IEnumerable<Guid> spendingsIdDelete = spendingsID._ids;
            bool isDelete = _financialRepository.DeleteSpendingsList(jarID, spendingsIdDelete);
            if (!isDelete  || !_financialRepository.Save())
            {
                result.message = "Delete spending failed ";
                return BadRequest(result);
            }

            result.success = true;
            result.message = "Delete spending list succeed";
            return Ok(result);

        }
    }
}