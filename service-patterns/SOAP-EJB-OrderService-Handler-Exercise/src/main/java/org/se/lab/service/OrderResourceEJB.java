package org.se.lab.service;

import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;

@WebService(name="OrderService")
@HandlerChain(file="handler-chain.xml")
@Stateless
public class OrderResourceEJB
{
	private final Logger LOG = Logger.getLogger(OrderResourceEJB.class);
	
	public OrderResourceEJB()
	{
		LOG.info(OrderResourceEJB.class.getName() + " constructor");
	}
	
	
	@WebMethod
	public void process(Order order)
	{
		LOG.info("process: " + order);

		// do something
	}
	
	
}
