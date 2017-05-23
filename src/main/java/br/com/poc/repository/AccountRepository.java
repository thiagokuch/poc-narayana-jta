package br.com.poc.repository;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.poc.entity.Account;

@Service
public class AccountRepository {
	
	@Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

	public Account save(Account account) {
		this.entityManagerFactory.createEntityManager().persist(account);
		return account;
	}
}
