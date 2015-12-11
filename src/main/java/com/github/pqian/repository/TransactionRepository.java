package com.github.pqian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.pqian.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	@Query("select t.id from Transaction t where t.type=?1")
	List<Long> findIdsByType(String type);
	
	@Query("select sum(t.amount) from Transaction t where t.id=?1 or t.parent.id=?1 and t.amount!=null")
	Double getSumById(long id);
}
