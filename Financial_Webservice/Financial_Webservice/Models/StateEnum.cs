using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
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

    public static class StateEnumExtension
    {
        public static String getStateDescription(this StateEnum stateEnum)
        {
            FieldInfo fi = stateEnum.GetType().GetField(stateEnum.ToString());
            DescriptionAttribute[] attributes = (DescriptionAttribute[])fi.
                GetCustomAttributes(typeof(DescriptionAttribute), false);
            if (attributes.Length > 0)
            {
                return attributes[0].Description;
            }
            else
            {
                return stateEnum.ToString();
            }
        }
    }
}
