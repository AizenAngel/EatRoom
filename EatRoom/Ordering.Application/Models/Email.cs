using System;

namespace Ordering.Application.Models
{
    public class Email
    {
        public string To { get; set; }
        public string Subject { get; set; }
        public string Body { get; set; }

        public Email(string to, string subject, string body)
        {
            To = to ?? throw new ArgumentNullException(nameof(to));
            Subject = subject ?? throw new ArgumentNullException(nameof(subject));
            Body = body ?? throw new ArgumentNullException(nameof(body));
        }
    }
}
