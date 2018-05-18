using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class Notification
    {
        [Key]
        public Guid _id { get; set; }
        public string name { get; set; }
        public DateTime date { get; set; }
        [ForeignKey("userID")]
        public User user { get; set; }
        public Guid userID { get; set; }
    }
}
