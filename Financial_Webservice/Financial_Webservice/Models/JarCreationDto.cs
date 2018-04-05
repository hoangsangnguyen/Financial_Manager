using Financial_Webservice.Entities;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace Financial_Webservice.Models
{
    public class JarCreationDto
    {
        public JarCreationDto(int index)
        {
            switch(index)
            {
                case 0:
                    type = TypeEnum.NHU_CAU_THIET_YEU.getTypeDescription();
                    break;
                case 1:
                    type = TypeEnum.TIET_KIEM_DAI_HAN.getTypeDescription();
                    break;
                case 2:
                    type = TypeEnum.GIAO_DUC.getTypeDescription();
                    break;
                case 3:
                    type = TypeEnum.THU_HUONG.getTypeDescription();
                    break;
                case 4:
                    type = TypeEnum.CHO_DI.getTypeDescription();
                    break;
                case 5:
                    type = TypeEnum.TU_DO_TAI_CHINH.getTypeDescription();
                    break;
                
            }
        }

        public string type { get; set; }

        public double incomes { get; set; }

        public double debts { get; set; }

        public double spendings { get; set; }

       
      
    }
}
