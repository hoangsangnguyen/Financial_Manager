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
    [Route("api/users/{userID}/jars/{jarID}/Debts")]
    public class DebtsController : Controller
    {
        private IFinancialRepository _financialRepository;

        public DebtsController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet]
        public IActionResult GetDebtsForJar([FromHeader] string token, Guid userID, Guid jarID, DebtResourceParameters debtResourceParameters )
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

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jars not found";
                return BadRequest(result);
            }

            var debtsFromRepo = _financialRepository.GetDebtForJar(jarID, debtResourceParameters);
            var debtToReturn = Mapper.Map<IEnumerable<DebtDto>>(debtsFromRepo);

            result.success = true;
            result.message = "Get debts succeed";
            result.results = debtToReturn;
            return Ok(result);
        }

        [HttpGet("{id}", Name = "GetDebtByID")]
        public IActionResult GetDebtById([FromHeader] string token, Guid userID, Guid jarID, Guid id)
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

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jars not found";
                return BadRequest(result);
            }

            var debtFromRepo = _financialRepository.GetDebtByID(jarID, id);
            if (debtFromRepo == null)
            {
                result.message = "Not found";
                return NotFound(result);
            }

            var debtToReturn = Mapper.Map<DebtDto>(debtFromRepo);
            result.success = true;
            result.message = "Get debt succeed";
            result.results = debtToReturn;
            return Ok(result);
        }

        [HttpPost]
        public IActionResult CreateDebt([FromHeader] string token, Guid userID, Guid jarID, [FromBody] DebtCreationDto debt )
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

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jars not found";
                return BadRequest(result);
            }

            var debtEntity = Mapper.Map<Entities.Debt>(debt);
            bool isAdded = _financialRepository.AddDebt(jarID, debtEntity);
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Create debt failed";
                return Ok(result);
            }

            var debtToReturn = Mapper.Map<DebtDto>(debtEntity);
            return CreatedAtRoute("GetDebtByID", new { userID = userID, jarID = jarID, id = debtToReturn._id },
                debtToReturn);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateDebt([FromHeader] string token, Guid userID, Guid jarID, Guid id, [FromBody] DebtUpdationDto debt )
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

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jars not found";
                return BadRequest(result);
            }

            var debtEntity = _financialRepository.GetDebtByID(jarID, id);
            if (debtEntity == null)
            {
                result.message = $"Debt id {id} not found";
                return NotFound(result);
            }

            Mapper.Map(debt, debtEntity);
            bool isUpdate = _financialRepository.UpdateDebt(jarID, debtEntity);
            if (!isUpdate)
            {
                result.message = "Can not update debt, please check your amount number";
                return BadRequest(result);
            }

            if (!_financialRepository.Save())
            {
                result.message = "Update debt failed on saving";
                return Ok(result);
            }

            var debtToReturn = Mapper.Map<DebtDto>(debtEntity);
            result.success = true;
            result.message = "Update debt succeed";
            result.results = debtToReturn;
            return Ok(result);
            
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteDebt([FromHeader] string token, Guid userID, Guid jarID, Guid id)
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

            if (!_financialRepository.JarExists(userID, jarID))
            {
                result.message = "Jars not found";
                return BadRequest(result);
            }

            var debtEntity = _financialRepository.GetDebtByID(jarID, id);
            if (debtEntity == null)
            {
                result.message = "Debt not found ";
                return NotFound(result);
            }

            bool isDeleted = _financialRepository.DeleteDebt(jarID, debtEntity);
            if (!isDeleted || !_financialRepository.Save())
            {
                result.message = "Delete debt failed";
                return Ok(result);
            }

            result.success = true;
            result.message = "Delete debt succeed";
            return Ok(result);
        }

    }


}