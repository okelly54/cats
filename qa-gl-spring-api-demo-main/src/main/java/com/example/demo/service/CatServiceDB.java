package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cat;
import com.example.demo.exception.CatNotFoundException;
import com.example.demo.repo.CatRepo;

@Primary
@Service
public class CatServiceDB implements CatService {
	private CatRepo repo;

	public CatServiceDB(CatRepo repo) {
		this.repo = repo;
	}

	@Override
	public Cat createCat(Cat c) {
		return this.repo.save(c);
	}

	@Override
	public Cat getById(int id) {
		return this.repo.findById((long) id).orElseThrow(() -> new CatNotFoundException());
	}

	@Override
	public List<Cat> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Cat delete(int id) {
		Cat removed = this.getById(id);
		this.repo.deleteById((long) id);
		return removed;
	}

	@Override
	public Cat update(int id, String name, Boolean hasWhiskers, Boolean evil, Integer length) {
		Cat c = this.getById(id);

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
