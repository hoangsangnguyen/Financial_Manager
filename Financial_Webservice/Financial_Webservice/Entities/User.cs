using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public class User
    {
        [Key]
        public Guid _id { get; set; }
        [Required]
        [MaxLength(50)]
        public string firstName { get; set; }
        [Required]
        [MaxLength(50)]
        public string lastName { get; set; }
        [EmailAddress]
        public string email { get; set; }
        [Phone]
        public string phone { get; set; }
        [Required]
        [MaxLength(50)]
        public string userName { get; set; }
        [Required]
        public string password { get; set; }
        public string token { get; set; }

        public string avatarUrl { get; set; }

        public ICollection<Jar> Jars { get; set; } = new List<Jar>();
    }
}
