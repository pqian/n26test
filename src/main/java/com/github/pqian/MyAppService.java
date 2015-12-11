package com.github.pqian;


import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.annotations.Logging;

import com.github.pqian.entity.Transaction;

@Path("/")
@Logging
public interface MyAppService {
	
	@PUT
	@Path("transaction/{transaction_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	StatusValue createTransaction(@PathParam("transaction_id") long id, @Valid Transaction transaction);
	
	@GET
	@Path("transaction/{transaction_id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	Transaction getTransactionById(@PathParam("transaction_id") long id);

	@GET
	@Path("types/{type}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	List<Long> getTransactionIdsByType(@PathParam("type") String type);

	@GET
	@Path("sum/{transaction_id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	SumValue getSumById(@PathParam("transaction_id") long id);
	
}
