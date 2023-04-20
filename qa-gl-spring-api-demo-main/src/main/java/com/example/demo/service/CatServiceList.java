package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cat;

@Service
public class CatServiceList implements CatService {

	List<Cat> cats = new ArrayList<>();

	@Override
	public Cat createCat(Cat c) {
		this.cats.add(c);

		return this.cats.get(cats.size() - 1);
	}

	@Override
	public Cat get(int id) {
		return this.cats.get(id);
	}

	@Override
	public List<Cat> getAll() {
		return this.cats;
	}

	@Override
	public Cat delete(int id) {
		return this.cats.remove(id);
	}

	@Override
	public Cat update(int id, Boolean hasWhiskers, String name, Boolean evil, Integer length) {
		Cat c = cats.get(id);

		if (hasWhiskers != null)
			c.setHasWhiskers(hasWhiskers);
		if (name != null)
			c.setName(name);
		if (evil != null)
			c.setEvil(evil);
		if (length != null)
			c.setLength(length);

		return c;
	}
}
