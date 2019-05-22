package capstone.p2plend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigchaindb.builders.BigchainDbConfigBuilder;

import capstone.p2plend.entity.Account;
import capstone.p2plend.exception.AccountNotFoundException;
import capstone.p2plend.repo.AccountRepository;

@RestController
public class AccountController {
	
	private final AccountRepository repository;

	AccountController(AccountRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/accounts")
	List<Account> all() {
		return repository.findAll();
	}

	@PostMapping("/accounts")
	Account newAccount(@RequestBody Account newAccount) {
		return repository.save(newAccount);
	}

	// Single item

	@GetMapping("/accounts/{id}")
	Account one(@PathVariable Integer id) {

		return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
	}

	@DeleteMapping("/accounts/{id}")
	void deleteAccount(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
	
	void bigChainDB() {
	}
}
