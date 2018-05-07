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
            app.UseStaticFiles();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseMvc();
            
            app.SeedData();
            IEnumerable<Entities.Type> types = context.Types.ToList();
            IEnumerable<State> states = context.States.ToList();

            AutoMapper.Mapper.Initialize(cfg =>
            {
                #region Users
                cfg.CreateMap<Entities.User, Models.UserDto>();
                cfg.CreateMap<Models.UserCreationDto, Entities.User>();
                cfg.CreateMap<Models.UserUpdattionDto, Entities.User>()
                    .ForMember(dest => dest.firstName, opts => opts.Condition(src => src.firstName != null))
                    .ForMember(dest => dest.lastName, opts => opts.Condition(src => src.lastName != null))
                    .ForMember(dest => dest.email, opts => opts.Condition(src => src.email != null))
                    .ForMember(dest => dest.phone, opts => opts.Condition(src => src.phone != null))
                    .ForMember(dest => dest.password, opts => opts.Condition(src => src.password != null))
                    .ForMember(dest => dest.avatarUrl, opts => opts.Condition(src => src.avatarUrl != null))
                    .ForMember(dest => dest.userName, opts => opts.Ignore());
                    
                #endregion

                #region Jars
                cfg.CreateMap<Entities.Jar, Models.JarDto>()
                    .ForMember(dest => dest.type, opt => opt.MapFrom(scr => scr.GetTypeNameFromID(types)))
                    .ForMember(dest => dest.incomes, opt => opt.MapFrom(scr => scr.GetIncomeAmount()))
                    .ForMember(dest => dest.posDebts, opt => opt.MapFrom(scr => scr.GetPosDebtAmount()))
                    .ForMember(dest => dest.negWaittingDebts, opt => opt.MapFrom(scr => scr.GetNegWaittingDebtAmount(states)))
                    .ForMember(dest => dest.negReadyDebts, opt => opt.MapFrom(scr => scr.GetNegReadyDebtAmount(states)))
                    .ForMember(dest => dest.negDoneDebts, opt => opt.MapFrom(scr => scr.GetNegDoneDebtAmount(states)))
                    .ForMember(dest => dest.availableAmount, opt => opt.MapFrom(scr => scr.GetAvaiableAmount(states)))
                    .ForMember(dest => dest.spendings, opt => opt.MapFrom(scr => scr.GetSpendingAmount()));

                cfg.CreateMap<Models.JarCreationDto, Entities.Jar>()
                    .ForMember(dest => dest.type, opt => opt.Ignore())
                    .ForMember(dest =>dest.typeID, opt => opt.MapFrom(scr => scr.GetTypeIDFromName(types)))
                    .ForMember(dest => dest.incomes, opt => opt.Ignore())
                    .ForMember(dest => dest.debts, opt => opt.Ignore())
                    .ForMember(dest => dest.spendings, opt => opt.Ignore());

                cfg.CreateMap<IEnumerable<InCome>, Models.JarDto>()
                    .ForMember(dest => dest.incomes, opt => opt.MapFrom(scr => scr.GetIncomeAmount()))
                    .ForMember(dest => dest.type, opt => opt.Ignore())
                    .ForMember(dest => dest.posDebts, opt => opt.Ignore())
                    .ForMember(dest => dest.negWaittingDebts, opt => opt.Ignore())
                    .ForMember(dest => dest.negReadyDebts, opt => opt.Ignore())
                    .ForMember(dest => dest.negDoneDebts, opt => opt.Ignore())
                    .ForMember(dest => dest.availableAmount, opt => opt.Ignore())
                    .ForMember(dest => dest.spendings, opt => opt.Ignore());

                #endregion

                #region Income
                cfg.CreateMap<Entities.InCome, Models.InComeDto>();
                cfg.CreateMap<Models.IncomeCreationDto, Entities.InCome>();
                #endregion

                #region Debt
                cfg.CreateMap<Entities.Debt, Models.DebtDto>()
                    .ForMember(dest => dest.state, opt => opt.MapFrom(scr => scr.GetStateNameFromID(states)));
                cfg.CreateMap<Models.DebtCreationDto, Entities.Debt>()
                    .ForMember(dest => dest.state, opt => opt.Ignore())
                    .ForMember(dest => dest.stateID, opt => opt.MapFrom(scr => scr.GetStateIdFromName(states)));
                cfg.CreateMap<Models.DebtUpdationDto, Entities.Debt>()
                    .ForMember(dest => dest.state, opt => opt.Ignore())
                    .ForMember(dest => dest.stateID, opt => opt.MapFrom(scr => scr.GetStateIdFromName(states)));
                #endregion

                #region State
                cfg.CreateMap<Entities.State, Models.StateDto>();
                #endregion

                #region Type
                cfg.CreateMap<Entities.Type, Models.TypeDto>();
                #endregion

                #region Spending
                cfg.CreateMap<Entities.SpendingDetail, Models.SpendingDetailDto>();
                cfg.CreateMap<Models.SpendingCreationDto, Entities.SpendingDetail>();
                #endregion

                #region images
                cfg.CreateMap<Entities.Image, Models.ImageDto>();
                cfg.CreateMap<Models.ImageDto, Image>();

                #endregion
            });
        }
    }
}
