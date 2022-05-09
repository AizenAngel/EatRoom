using MediatR;
using Ordering.Application.Contracts.Infrasturucture;
using Ordering.Application.Contracts.Infrasturucture.Factories;
using Ordering.Application.Contracts.Persistance;
using Ordering.Application.Models;
using Ordering.Domain.Aggregates;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Ordering.Application.Features.Orders.Commands.CreateOrder
{
    class CreateOrderCommandHandler : IRequestHandler<CreateOrderCommand, int>
    {
        private readonly IOrderRepository _orderRepsitory;
        private readonly IOrderFactory _orderFactory;
        private readonly IEmailService _emailService;

        public CreateOrderCommandHandler(IOrderRepository orderRepsitory, IOrderFactory orderFactory, IEmailService emailService)
        {
            _orderRepsitory = orderRepsitory ?? throw new ArgumentNullException(nameof(orderRepsitory));
            _orderFactory = orderFactory ?? throw new ArgumentNullException(nameof(orderFactory));
            _emailService = emailService ?? throw new ArgumentNullException(nameof(emailService));
        }

        public async Task<int> Handle(CreateOrderCommand request, CancellationToken cancellationToken)
        {
            var order = _orderFactory.Create(request);
            Order newOrder = await _orderRepsitory.AddAsync(order);

            await SendEmail(newOrder);

            return newOrder.Id;
        }

        private async Task SendEmail(Order newOrder)
        {
            var email = new Email(newOrder.Email, "", "");
            try
            {
                await _emailService.SendEmail(email);
            }
            catch(Exception _)
            {
                /*If exception*/
                
                throw;
               
            }
        }
    }
}
