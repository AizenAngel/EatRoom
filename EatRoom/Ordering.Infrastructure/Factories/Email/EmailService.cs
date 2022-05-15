using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Ordering.Application.Contracts.Infrasturucture;

namespace Ordering.Infrastructure.Factories.Email
{
    public class EmailService : IEmailService
    {
        public Task<bool> SendEmail(Application.Models.Email emailRequest)
        {
            throw new NotImplementedException();
        }
    }
}
