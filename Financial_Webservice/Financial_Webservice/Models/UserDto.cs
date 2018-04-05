using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class UserDto
    {
        public Guid _id { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string email { get; set; }
        public string phone { get; set; }
        public string userName { get; set; }
        public string password { get; set; }
        public string token { get; set; }
    }
}
