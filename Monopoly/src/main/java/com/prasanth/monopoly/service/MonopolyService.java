package com.prasanth.monopoly.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanth.monopoly.Dao.MonopolyDao;
import com.prasanth.monopoly.dto.Dies;

@Service
public class MonopolyService {

	@Autowired
	private MonopolyDao dao;
	
	@Transactional
	public String getBalance() {
		return dao.getBalance();
	}

	@Transactional
	public String dies(Dies dies) {
		return dao.dies(dies);
	}

	@Transactional
	public String startCrossed(Dies dies) {
		return dao.startCrossed(dies);
	}

	@Transactional
	public String rentPaid(Dies dies) {
		return dao.rentPaid(dies);
	}

	@Transactional
	public String restart() {
		return dao.restart();
	}
}
