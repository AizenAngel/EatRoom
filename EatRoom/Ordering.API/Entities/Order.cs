using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ordering.API.Entities
{
    public class Order
    {
		public int Id { get; set; }
		public int UserId { get; set; }
		public int RestarauntId { get; set; }
		
		public double Price { get; set; }
	    public StateEnum State { get; set; }
		public int DelivererId { get; set; }
	}

	public enum StateEnum
    {
		Preparing,
		Delivering,
		Delivered
	}
}
