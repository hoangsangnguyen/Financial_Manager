using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Financial_Webservice.Entities;
using Financial_Webservice.Helpers;
using Financial_Webservice.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;

namespace Financial_Webservice
{
    public class Startup
    {
        public static IConfigurationRoot Configuration;

        public Startup(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appSettings.json", optional: false, reloadOnChange: true)
                .AddJsonFile($"appSettings.{env.EnvironmentName}.json", optional: true, reloadOnChange: true)
                .AddEnvironmentVariables();

            Configuration = builder.Build();
        }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc();
            var connectionString = Configuration["connectionStrings:financialDBConnectionString"];
            services.AddDbContext<FinancialDBContext>(o => o.UseSqlServer(connectionString));

            // register the repository
            services.AddScoped<IFinancialRepository, FinancialRepository>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, FinancialDBContext context, IFinancialRepository financialRepository)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseMvc();
            
            app.SeedData();
            IEnumerable<Entities.Type> types = context.Types.ToList();
            IEnumerable<InCome> inComes = context.Incomes.ToList();
            IEnumerable<Debt> debts = context.Debts.ToList();
            IEnumerable<SpendingDetail> spendings = context.SpendingsDetail.ToList();

            AutoMapper.Mapper.Initialize(cfg =>
            {
                #region Users
                cfg.CreateMap<Entities.User, Models.UserDto>();
                cfg.CreateMap<Models.UserCreationDto, Entities.User>();
                #endregion

                #region Jars
                cfg.CreateMap<Entities.Jar, Models.JarDto>()
                    .ForMember(dest => dest.type, opt => opt.MapFrom(scr => scr.GetTypeNameFromID(types)))
                    .ForMember(dest => dest.incomes, opt => opt.MapFrom(scr => scr.GetIncomeAmount()))
                    .ForMember(dest => dest.debts, opt => opt.MapFrom(scr => scr.GetDebtAmount(debts)))
                    .ForMember(dest => dest.spendings, opt => opt.MapFrom(scr => scr.GetSpendingAmount(spendings)));

                cfg.CreateMap<Models.JarCreationDto, Entities.Jar>()
                    .ForMember(dest => dest.type, opt => opt.Ignore())
                    .ForMember(dest =>dest.typeID, opt => opt.MapFrom(scr => scr.GetTypeIDFromName(types)))
                    .ForMember(dest => dest.incomes, opt => opt.Ignore())
                    .ForMember(dest => dest.debts, opt => opt.Ignore())
                    .ForMember(dest => dest.spendings, opt => opt.Ignore());

                cfg.CreateMap<IEnumerable<InCome>, Models.JarDto>()
                    .ForMember(dest => dest.incomes, opt => opt.MapFrom(scr => scr.GetIncomeAmount()))
                    .ForMember(dest => dest.type, opt => opt.Ignore())
                    .ForMember(dest => dest.debts, opt => opt.Ignore())
                    .ForMember(dest => dest.spendings, opt => opt.Ignore());

                #endregion

                #region Income
                cfg.CreateMap<Entities.InCome, Models.InComeDto>();
                cfg.CreateMap<Models.IncomeCreationDto, Entities.InCome>();
                #endregion

                #region Debt
                cfg.CreateMap<Entities.Debt, Models.DebtDto>()
                    .ForMember(dest => dest.state, opt => opt.MapFrom(scr => scr.state.name));
                #endregion

                #region State
                cfg.CreateMap<Entities.State, Models.StateDto>();
                #endregion

                #region Type
                cfg.CreateMap<Entities.Type, Models.TypeDto>();
                #endregion

                #region Spending
                cfg.CreateMap<Entities.SpendingDetail, Models.SpendingDetailDto>();
                #endregion

            });
        }
    }
}
