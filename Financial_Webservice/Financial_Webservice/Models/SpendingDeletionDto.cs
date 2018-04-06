using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class SpendingDeletionDto
    {
        public IEnumerable<Guid> _ids{ get; set; }
    }
}
