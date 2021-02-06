package com.prasanth.monopoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.monopoly.dto.Dies;
import com.prasanth.monopoly.service.MonopolyService;

@RestController
@RequestMapping("/")
public class MonopolyController {

	@Autowired
	private MonopolyService service;

	@RequestMapping("start")
	public String start() {
		String bal = service.getBalance();
		String[] split = bal.split(" ");

		return "Game Started" + " player1 Balance = " + split[0] + " player2 Balance = " + split[1];
	}

	@PostMapping("start/dies")
	public String dies(@RequestBody() Dies dies) {
		String bal = service.dies(dies);
		if (bal.startsWith("Game")) {
			return bal;
		} else if (dies.getPlayer() == 123 && !(bal.equals("Invalide Input"))) {
			return "Player1 buy " + dies.getPlace() + " Player1 Remaining Balance " + bal;
		} else if (dies.getPlayer() == 321 && !(bal.equals("Invalide Input"))) {
			return "Player2 buy " + dies.getPlace() + " Player2 Remaining Balance " + bal;
		}
		return bal;
	}

	@PostMapping("start/crossed")
	public String startCrossed(@RequestBody() Dies dies) {

		String bal = service.startCrossed(dies);
		if (bal.startsWith("Game")) {
			return bal;
		} else if (dies.getPlayer() == 123 && !(bal.equals("Invalide Input"))) {
			return "Player1 crossed " + dies.getPlace() + " Player1 Remaining Balance " + bal;
		} else if (dies.getPlayer() == 321 && !(bal.equals("Invalide Input"))) {
			return "Player2 crossed " + dies.getPlace() + "Player2 Remaining Balance " + bal;
		}
		return bal;
	}

	@PostMapping("start/rent")
	public String rentPaid(@RequestBody() Dies dies) {

		String bal = service.rentPaid(dies);
		if (bal.startsWith("Game")) {
			return bal;
		} else if (bal.contains("won") && dies.getPlayer() == 123) {
			return "Player1 Remaining balance 0 "+bal;
		}else if (bal.contains("won") && dies.getPlayer() == 321) {
			return "Player2 Remaining balance 0 "+bal;
		}
		else if (dies.getPlayer() == 321 && !(bal.equals("Invalide Input"))) {
			return "Player2 paid rent: " + dies.getAmount() + " to stay " + dies.getPlace()
					+ " Player1 Remaining Balance " + bal;
		} else if (dies.getPlayer() == 123 && !(bal.equals("Invalide Input"))) {
			return "Player1 paid rent: " + dies.getAmount() + " to stay " + dies.getPlace()
					+ " Player2 Remaining Balance " + bal;
		}
		return bal;

	}

	@RequestMapping("start/reset")
	public String restart() {
		String bal = service.restart();
		String[] split = bal.split(" ");

		return "Game restarted" + " player1 Balance = " + split[0] + " player2 Balance = " + split[1];
	}

}
