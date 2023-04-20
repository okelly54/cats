package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cat;
import com.example.demo.exception.CatNotFoundException;
import com.example.demo.repo.CatRepo;

// Make this the primary service used by the controller
@Primary
@Service
public class CatServiceDB implements CatService {

	private CatRepo repo;

	public CatServiceDB(CatRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Cat createCat(Cat c) {
		return this.repo.save(c);
	}

	@Override
	public Cat get(int id) {
		// findById returns an Optional
		return this.repo.findById((long) id).orElseThrow(() -> new CatNotFoundException());
		// or a shortcut is (CatNotFoundException::new)
	}

	@Override
	public List<Cat> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Cat delete(int id) {
		Cat removed = this.get(id);
		this.repo.deleteById((long) id);
		return removed;
	}

	@Override
	public Cat update(int id, Boolean hasWhiskers, String name, Boolean evil, Integer length) {
		Cat c = this.get(id);

		if (hasWhiskers != null)
			c.setHasWhiskers(hasWhiskers);
		if (name != null)
			c.setName(name);
		if (evil != null)
			c.setEvil(evil);
		if (length != null)
			c.setLength(length);

		return this.repo.save(c);
	}

}
