using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public enum StateEnum
    {
        [Description("Done")]
        DONE,

        [Description("Ready")]
        READY,

        [Description("Waiting")]
        WATING

    }
}
