using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Financial_Webservice.Services;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Financial_Webservice.Models;
using AutoMapper;
using System.IO;
using Financial_Webservice.Entities;

namespace Financial_Webservice.Controllers
{
    [Produces("application/json")]
    [Route("api/Users/{userID}/Images")]
    public class ImagesController : Controller
    {
        private IFinancialRepository _financialRepository;
        private IHostingEnvironment _environment;

        public ImagesController(IHostingEnvironment environment, IFinancialRepository financialRepository)
        {
            _environment = environment;
            _financialRepository = financialRepository;
        }

        [HttpPost]
        public async Task<IActionResult> UploadImage([FromForm] IFormFile file, [FromHeader] string token, Guid userID)
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

            //Image image = new Image();
            //image.fileName = file.FileName;
            //image.size = file.Length;

            //Stream stream = file.OpenReadStream();
            //BinaryReader binaryReader = new BinaryReader(stream);
            //byte[] bytes = binaryReader.ReadBytes((int)stream.Length);
            //image.data = bytes;

            //var response = _financialRepository.SaveImage(image);
            //if (!response || !_financialRepository.Save())
            //{
            //    result.success = false;
            //    result.message = "Upload image failed";
            //    return BadRequest(result);
            //}

            //var imageToReturn = Mapper.Map<ImageDto>(image);
            //result.success = true;
            //result.message = "Upload image succeed";
            //result.results = image;

            if (string.IsNullOrWhiteSpace(_environment.WebRootPath))
            {
                _environment.WebRootPath = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot");
            }

            var uploads = Path.Combine(_environment.WebRootPath);
            string url = Request.Scheme + "://" + Request.Host + "/";
            if (file.Length > 0)
            {
                String timeStamp = GetTimestamp(DateTime.Now);
                string fileName = timeStamp + "_" + file.FileName;
                url += fileName;

                using (var fileStream = new FileStream(Path.Combine(uploads, fileName), FileMode.Create))
                {
                    await file.CopyToAsync(fileStream);
                }
            }
            result.success = true;
            result.message = "Upload image succeed";
            result.results = new { url};
            return Ok(result);
        }
        public static String GetTimestamp(DateTime value)
        {
            return value.ToString("yyyyMMddHHmmssffff");
        }


    }
}