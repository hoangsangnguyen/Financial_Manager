using System;
using System.Linq;
using System.Web.Http;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using FinancialService.Models;

namespace FinancialService.Controllers
{
    public class UserController : ApiController
    {
        [HttpGet]
        public ResultModel GetUserLists()
        {
            DbFinancialDataContext db = new DbFinancialDataContext();
            ResultModel result = new ResultModel()
            {
                success = true,
                results = db.Users.ToList()
            };
            return result;
        }

        [ActionName("SignUp")]
        [HttpPost]
        public ResultModel SignUp([FromBody] Users user)
        {
            DbFinancialDataContext db = new DbFinancialDataContext();
            var response = db.sp_SignUp(user.firstName, user.lastName, user.userName, user.password, user.phone, user.email);
            ResultModel result = new ResultModel();
            result.success = (response == 0);
            if (result.success)
            {
                Users userDB = db.Users.FirstOrDefault(x => x.userName == user.userName);
                userDB.token = createToken(userDB.userName);
                db.SubmitChanges();
                result.message = "SignUp success";
                result.results = userDB;
            } else
            {
                if (response == 1)
                    result.message = "Username already exists !";
                else
                    result.message = "Email already exists !";
            }

            return result;
        }

        [ActionName("Login")]
        [HttpPost]
        public ResultModel Login([FromBody] Users user)
        {
            DbFinancialDataContext db = new DbFinancialDataContext();
            var response = db.sp_Login(user.userName, user.password);
            ResultModel result = new ResultModel();
            result.success = (response == 0);
            if (result.success)
            {
                Users userDB = db.Users.FirstOrDefault(x => x.userName == user.userName);
                result.results = userDB;
            }
            return result;
        }

        private string createToken(string username)
        {
            //Set issued at date
            DateTime issuedAt = DateTime.UtcNow;
            //set the time when it expires
            DateTime expires = DateTime.UtcNow.AddDays(7);

            //http://stackoverflow.com/questions/18223868/how-to-encrypt-jwt-security-token
            var tokenHandler = new JwtSecurityTokenHandler();

            //create a identity and add claims to the user which we want to log in
            ClaimsIdentity claimsIdentity = new ClaimsIdentity(new[]
            {
                new Claim(ClaimTypes.Name, username)
            });

            const string sec = "401b09eab3c013d4ca54922bb802bec8fd5318192b0a75f201d8b3727429090fb337591abd3e44453b954555b7a0812e1081c39b740293f765eae731f5a65ed1";
            var now = DateTime.UtcNow;
            var securityKey = new Microsoft.IdentityModel.Tokens.SymmetricSecurityKey(System.Text.Encoding.Default.GetBytes(sec));
            var signingCredentials = new Microsoft.IdentityModel.Tokens.SigningCredentials(securityKey, Microsoft.IdentityModel.Tokens.SecurityAlgorithms.HmacSha256Signature);


            //create the jwt
            var token =
                (JwtSecurityToken)
                    tokenHandler.CreateJwtSecurityToken(issuer: "http://localhost:50191", audience: "http://localhost:50191",
                        subject: claimsIdentity, notBefore: issuedAt, expires: expires, signingCredentials: signingCredentials);
            var tokenString = tokenHandler.WriteToken(token);

            return tokenString;
        }
    }
}
