package com.github.pqian.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "/applicationContext.xml" })
@ActiveProfiles("test")
@TransactionConfiguration
public class TransactionRepositoryTest {
	
	@Autowired
	private TransactionRepository repo;

	@Test
	public void testFindIdsByType() {
		List<Long> ids = repo.findIdsByType("accessories");
		Assert.assertEquals(1, ids.size());
		Assert.assertTrue(ids.contains(2L));
	}
	
	@Test
	public void testGetSumById() {
		Assert.assertEquals(new Double(550), repo.getSumById(1));
		Assert.assertEquals(new Double(50), repo.getSumById(2));
	}
	
}
