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
using System.Reflection;
using Financial_Webservice.Helpers;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
    [Route("api/users/{userID}/Jars")]
    public class JarsController : Controller
    {
        private IFinancialRepository _financialRepository;

        public JarsController(IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet(Name ="GetJars")]
        public IActionResult GetJarsForUser([FromHeader] string token, Guid userID)
        {
            ResultDto result = new ResultDto();

            if(!_financialRepository.checkAuthenticated(token, userID))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(userID))
            {
                result.message = "User not found";
                return NotFound(result);
            }

            var jarsFromRepo = _financialRepository.GetJars(userID);
            if (jarsFromRepo == null || jarsFromRepo.Count() == 0) // user hasn't created jar
            {
                var jarsNew = new List<JarDto>();
                for (int i = 0; i < 6; i++)
                {
                    var jar = new JarCreationDto(i);
                    var jarEntity = Mapper.Map<Jar>(jar);

                    bool isAdded = _financialRepository.AddJar(userID, jarEntity);
                    if (!isAdded || !_financialRepository.Save())
                    {
                        result.message = "Create jar failed";
                        return Ok(result);
                    }

                    var jarToReturn = Mapper.Map<JarDto>(jarEntity);
                    jarsNew.Add(jarToReturn);
                }

                return CreatedAtRoute("GetJars", new { userID }, jarsNew);
                    
            }

            var jarsToReturn = Mapper.Map<IEnumerable<Models.JarDto>>(jarsFromRepo);
            result.success = true;
            result.message = "Get jars succeed";
            result.results = jarsToReturn;
            return Ok(result);

        }

        [HttpGet("{id}", Name = "GetJar")]
        public IActionResult GetJar([FromHeader] string token, Guid userID, Guid id)
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
                return NotFound(result);
            }

            if (!_financialRepository.JarExists(id))
            {
                result.message = "Jar not found";
                return NotFound(result);
            }

            var jarFromRepo = _financialRepository.GetJar(id);
            var jarToReturn = Mapper.Map<JarDto>(jarFromRepo);

            result.success = true;
            result.message = "Get jar succeed";
            result.results = jarToReturn;

            return Ok(result);
        }

        [HttpPost]
        public IActionResult CreateJar([FromHeader] string token, Guid userID, [FromBody] JarCreationDto jar)
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
                return NotFound(result);
            }
            
            if (jar == null)
            {
                return BadRequest(result);
            }

            var jarEntity = Mapper.Map<Jar>(jar);
            bool isAdded = _financialRepository.AddJar(userID, jarEntity);
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Create jar failed";
                return Ok(result);
            }

            var jarToReturn = Mapper.Map<JarDto>(jarEntity);
            return CreatedAtRoute("GetJar", new { userID = userID, id = jarToReturn._id },
                jarToReturn);
        }

    }
}