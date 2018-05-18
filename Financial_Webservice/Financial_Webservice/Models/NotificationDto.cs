using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class NotificationDto
    {
        public Guid _id { get; set; }
        public string name { get; set; }
        public DateTime date { get; set; }
    }
}
