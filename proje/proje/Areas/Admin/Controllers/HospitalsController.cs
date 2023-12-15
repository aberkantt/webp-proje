using Microsoft.AspNetCore.Mvc;

namespace proje.Areas.Admin.Controllers
{
    public class HospitalsController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
