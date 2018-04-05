using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public enum TypeEnum
    {
        [Description("Nhu cầu thiết yếu")]
        NHU_CAU_THIET_YEU,

        [Description("Tiết kiệm dài hạn")]
        TIET_KIEM_DAI_HAN,

        [Description("Giáo dục")]
        GIAO_DUC,

        [Description("Thụ hưởng")]
        THU_HUONG,

        [Description("Cho đi")]
        CHO_DI,

        [Description("Tự do tài chính")]
        TU_DO_TAI_CHINH,

        [Description("Tổng hợp")]
        TONG_HOP

    }

    public static class TypeEnumExtension
    {
        public static String getTypeDescription(this TypeEnum typeEnum)
        {
            FieldInfo fi = typeEnum.GetType().GetField(typeEnum.ToString());
            DescriptionAttribute[] attributes = (DescriptionAttribute[])fi.
                GetCustomAttributes(typeof(DescriptionAttribute), false);
            if (attributes.Length > 0)
            {
                return attributes[0].Description;
            }
            else
            {
                return typeEnum.ToString();
            }
        }
    }

}
