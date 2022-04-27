using Ordering.Application.Models;
using System.Threading.Tasks;

namespace Ordering.Application.Contracts.Infrasturucture
{
    interface IEmailService
    {
        Task<bool> SendEmail(Email emailRequest);
    }
}
