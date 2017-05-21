package br.com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poc.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
