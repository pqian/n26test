package com.github.pqian;

import java.util.List;

import javax.ws.rs.NotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.apache.cxf.annotations.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pqian.entity.Transaction;
import com.github.pqian.repository.TransactionRepository;

@Service("myAppService")
@Transactional
@Slf4j
@Logging
public class MyAppServiceImpl implements MyAppService {
	
	@Autowired
	private TransactionRepository repo;

	@Override
	public StatusValue createTransaction(long id, Transaction transaction) {
		if (repo.exists(id)) {
			log.error("The specified transaction id already exists, {}", id);
			return StatusValue.of("failed");
		}
		transaction.setId(id);

		Transaction parent = null;
		if (transaction.getParentId() != null) {
			parent = repo.findOne(transaction.getParentId());
			if (parent == null) {
				log.error("invalid parent id, {}", transaction.getParentId());
				return StatusValue.of("failed");
			}
		}
		transaction.setParent(parent);
		
		repo.save(transaction);
		return StatusValue.of("ok");
	}

	@Override
	@Transactional(readOnly = true)
	public Transaction getTransactionById(long id) {
		Transaction ret = repo.findOne(id);
		if (ret != null) {
			return ret;
		}
		log.warn("No transaction found by id {}", id);
		throw new NotFoundException("Transaction not found");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> getTransactionIdsByType(String type) {
		return repo.findIdsByType(type);
	}

	@Override
	@Transactional(readOnly = true)
	public SumValue getSumById(long id) {
		if (!repo.exists(id)) {
			log.warn("No transaction found by id {}", id);
			throw new NotFoundException("Transaction not found");
		}
		
		Double sum = repo.getSumById(id);
		return SumValue.of(sum != null ? sum : 0.0d);
	}
	

}
