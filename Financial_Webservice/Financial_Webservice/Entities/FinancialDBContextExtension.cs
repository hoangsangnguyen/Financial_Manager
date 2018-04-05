using Financial_Webservice.Models;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Financial_Webservice.Entities
{
    public static class FinancialDBContextExtension
    {
        public static void EnsureSeedDataForContext(this FinancialDBContext context)
        {
            if (context.Types.Count() > 0)
                return;

            // intit data
            var types = new List<Entities.Type>()
            {
                new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.NHU_CAU_THIET_YEU.getTypeDescription()
                },
                 new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.TIET_KIEM_DAI_HAN.getTypeDescription()
                },
                  new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.GIAO_DUC.getTypeDescription()
                },
                   new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.THU_HUONG.getTypeDescription()
                },
                    new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.CHO_DI.getTypeDescription()
                },
                     new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.TU_DO_TAI_CHINH.getTypeDescription()
                }

            };
            context.AddRange(types);
            context.SaveChanges();

        }

        public static void SeedData(this IApplicationBuilder builder)
        {
            var provider = builder.ApplicationServices;
            var scopeFactory = provider.GetRequiredService<IServiceScopeFactory>();

            using (var scope = scopeFactory.CreateScope())
            using (var context = scope.ServiceProvider.GetRequiredService<FinancialDBContext>())
            {
                // Use context to insert data in database...
                if (context.Types.Count() > 0)
                    return;

                // intit data
                var types = new List<Entities.Type>()
            {
                new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.NHU_CAU_THIET_YEU.getTypeDescription(),
                    percent = 0.55
                },
                 new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.TIET_KIEM_DAI_HAN.getTypeDescription(),
                    percent = 0.1
                },
                  new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.GIAO_DUC.getTypeDescription(),
                    percent = 0.1
                },
                   new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.THU_HUONG.getTypeDescription(),
                    percent = 0.1
                },
                    new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.CHO_DI.getTypeDescription(),
                    percent = 0.05
                },
                     new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.TU_DO_TAI_CHINH.getTypeDescription(),
                    percent = 0.1
                },
                     new Entities.Type()
                {
                    _id = Guid.NewGuid(),
                    name = TypeEnum.TONG_HOP.getTypeDescription(),
                    percent = 1
                }

            };
                context.AddRange(types);
                context.SaveChanges();
            }
        }
    }
}
