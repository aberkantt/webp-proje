using Hospital.Models;
using Hospital.Repositories.Interfaces;
using Hospital.Utilies;
using Hospital.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hospital.Services
{
    public class ApplicationUserService : IApplicationUserService
    {

        private IUnitOfWork _unitofwork;

        public ApplicationUserService(IUnitOfWork unitofwork)
        {
            _unitofwork = unitofwork;
        }

        public PagedResult<ApplicationUserViewModel> GetAll(int PageNumber, int PageSize)
        {
            var vm = new ApplicationUserViewModel();
            int totalCount;
            List<ApplicationUserViewModel> vmList=new List<ApplicationUserViewModel>();
            try
            {
                int ExcludeRecords = (PageSize * PageNumber) - PageSize;

                var modelList = _unitofwork.GenericRepository<ApplicationUser>().GetAll().Skip(ExcludeRecords).Take(PageSize).ToList();

                totalCount = _unitofwork.GenericRepository<ApplicationUser>().GetAll().ToList().Count;

                vmList = ConvertModelToViewModelList(modelList);
            }catch (Exception) 
            {
                throw;
            }
            var result = new PagedResult<ApplicationUserViewModel>
            {
                Data = vmList,
                TotalItems = totalCount,
                PageNumber = PageNumber,
                PageSize = PageSize
            };
            return result;
        }

        public PagedResult<ApplicationUserViewModel> GetAllDoctor(int PageNumber, int PageSize)
        {
            var vm = new ApplicationUserViewModel();
            int totalCount;
            List<ApplicationUserViewModel> vmList = new List<ApplicationUserViewModel>();
            try
            {
                int ExcludeRecords = (PageSize * PageNumber) - PageSize;

                var modelList = _unitofwork.GenericRepository<ApplicationUser>().GetAll(x=>x.IsDoctor==true).Skip(ExcludeRecords).Take(PageSize).ToList();

                totalCount = _unitofwork.GenericRepository<ApplicationUser>().GetAll(x => x.IsDoctor == true).ToList().Count;

                vmList = ConvertModelToViewModelList(modelList);
            }
            catch (Exception)
            {
                throw;
            }
            var result = new PagedResult<ApplicationUserViewModel>
            {
                Data = vmList,
                TotalItems = totalCount,
                PageNumber = PageNumber,
                PageSize = PageSize
            };
            return result;
        }

        public PagedResult<ApplicationUserViewModel> GetAllPatient(int PageNumber, int PageSize)
        {
            throw new NotImplementedException();
        }

        public PagedResult<ApplicationUserViewModel> SearchDoctor(int PageNumber, int PageSize, string Spicility = null)
        {
            throw new NotImplementedException();
        }
        private List<ApplicationUserViewModel> ConvertModelToViewModelList(List<ApplicationUser> modelList) 
        { 
            return modelList.Select(x=>new ApplicationUserViewModel(x)).ToList();
        }
    }
}
