using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Financial_Webservice.Helpers;
using Financial_Webservice.Models;
using Financial_Webservice.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Hosting;
using System.IO;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
    [Route("api/Users")]
    public class UsersController : Controller
    {
        private IFinancialRepository _financialRepository;

        public UsersController(IHostingEnvironment environment, IFinancialRepository financialRepository)
        {
            _financialRepository = financialRepository;
        }

        [HttpGet]
        public IActionResult GetUsers()
        {
            ResultDto result = new ResultDto();
            try
            {
                var usersFromRepo = _financialRepository.GetUsers();
                var usersToReturn = Mapper.Map<IEnumerable<UserDto>>(usersFromRepo);
                result.success = true;
                result.message = "Get all users success";
                result.results = usersToReturn;

                return Ok(result);

            }
            catch
            {
                return new StatusCodeResult(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpGet("{id}", Name = "GetUser")]
        public IActionResult GetUser(Guid id, [FromHeader] string token)
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.checkAuthenticated(token, id))
            {
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(id))
            {
                return NotFound(result);
            }

            var userFromRepo = _financialRepository.GetUser(id);
            if (userFromRepo == null)
            {
                result.message = "Get user failed";
                return NotFound(result);
            }

            var userToReturn = Mapper.Map<Models.UserDto>(userFromRepo);
            result.success = true;
            result.message = "Get user succeed";
            result.results = userToReturn;

            return Ok(result);
        }

        [HttpPost]
        public IActionResult CreateUser([FromBody] UserCreationDto user)
        {
            ResultDto result = new ResultDto();

            if (user == null)
            {
                return BadRequest(result);
            }

            var userEntity = Mapper.Map<Entities.User>(user);
            userEntity.token = TokenExtension.createToken(userEntity.userName);

            bool isAdded = _financialRepository.AddUser(userEntity);
            
            if (!isAdded || !_financialRepository.Save())
            {
                result.message = "Create user failed";
                return Ok(result);
            }

            var userToReturn = Mapper.Map<Models.UserDto>(userEntity);
            result.success = true;
            result.message = "Create user succeed";
            result.results = userToReturn;

            return CreatedAtRoute("GetUser", new { id = userToReturn._id, userToReturn.token },
                result);

        }

        [HttpPost("login")]
        public IActionResult Login([FromBody] LoginDto dto)
        {
            ResultDto result = new ResultDto();

            if (dto == null)
            {
                return BadRequest(result);
            }

            var userFromRepo = _financialRepository.login(dto.userName, dto.password);
            if (userFromRepo == null)
            {
                result.message = "Username or password wrong !";
                return NotFound(result);
            }

            var userToReturn = Mapper.Map<UserDto>(userFromRepo);
            result.success = true;
            result.message = "Login succeed";
            result.results = userToReturn;
            return Ok(result);
        }

        [HttpPut("{id}")]
        public IActionResult UploadAvatar ([FromHeader] string token, Guid id, [FromBody] UserUpdattionDto user )
        {
            ResultDto result = new ResultDto();
            if (!_financialRepository.checkAuthenticated(token, id))
            {
                result.message = "Token failed";
                return BadRequest(result);
            }

            if (!_financialRepository.UserExists(id))
            {
                result.message = "User not found";
                return BadRequest(result);
            }

            var userEntity = _financialRepository.GetUser(id);
            if (userEntity == null)
            {
                result.message = $"User id {id} not found";
                return NotFound(result);
            }

            Mapper.Map(user, userEntity);
            _financialRepository.UpdateUser(userEntity);
           
            if (!_financialRepository.Save())
            {
                result.message = "Update user failed on saving";
                return Ok(result);
            }

            var userToReturn = Mapper.Map<UserDto>(userEntity);
            result.success = true;
            result.message = "Update user succeed";
            result.results = userToReturn;
            return Ok(result);
        }
    }
}