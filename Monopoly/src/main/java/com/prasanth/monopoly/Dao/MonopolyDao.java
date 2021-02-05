package com.prasanth.monopoly.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prasanth.monopoly.dto.Dies;
import com.prasanth.monopoly.dto.Player1;
import com.prasanth.monopoly.dto.Player2;

@Repository
public class MonopolyDao {

	@Autowired
	private EntityManager entityManager;

	public String getBalance() {
		Session session = entityManager.unwrap(Session.class);
		
		Player1 player1 = new Player1();
		player1.setId(123);
		player1.setBalance(1000);
		
		session.saveOrUpdate(player1);
		
		Player2 player2 = new Player2();
		player2.setId(321);
		player2.setBalance(1000);
		
		session.saveOrUpdate(player2);
		
		Player1 p1 = session.get(Player1.class, 123);
		Player2 p2 = session.get(Player2.class, 321);
		return Integer.toString(p1.getBalance()) + " " + Integer.toString(p2.getBalance());
	}

	public String dies(Dies dies) {
		Session session = entityManager.unwrap(Session.class);
		List<Dies> d =  session.createQuery("from Dies", Dies.class).getResultList();

		if (!(d.get(0).getId() > 49)) {

			if (dies.getPlayer() == 123) {
				Player1 p1 = session.get(Player1.class, dies.getPlayer());
				int b1 = p1.getBalance();

				if (b1 > 0) {
					int updatedBal1 = b1 - dies.getAmount();
					p1.setBalance(updatedBal1);
					session.createQuery("update Player1 set balance=:newbal where id=123")
							.setParameter("newbal", updatedBal1).executeUpdate();
					session.save(dies);
					return Integer.toString(updatedBal1);
				} else {
					return "0 Player2 won the Game";
				}
			} else if (dies.getPlayer() == 321) {
				Player2 p2 = session.get(Player2.class, dies.getPlayer());
				int b2 = p2.getBalance();

				if (b2 > 0) {
					int updatedBal2 = b2 - dies.getAmount();
					p2.setBalance(updatedBal2);
					session.createQuery("update Player2 set balance=:newbal where id=321")
							.setParameter("newbal", updatedBal2).executeUpdate();
					session.save(dies);
					return Integer.toString(updatedBal2);

				} else {
					return "0 Player1 won the Game";
				}
			}
			return "Invalide Input";
		}else {
			Player1 p1 = session.get(Player1.class, 123);
			Player2 p2 = session.get(Player2.class, 123);
			if (p1.getBalance()>p2.getBalance()) {
				return "Game Over 50 turns exceed Player1 won the Game";
			}else {
			return "Game Over 50 turns exceed Player2 won the Game";
			}
		}
	}

	public String startCrossed(Dies dies) {
		Session session = entityManager.unwrap(Session.class);
		List<Dies> d = session.createQuery("from Dies", Dies.class).getResultList();

		if (!(d.get(0).getId() > 49)) {
		
		if (dies.getPlayer() == 123) {
			Player1 p1 = session.get(Player1.class, dies.getPlayer());
			int b1 = p1.getBalance();

			if (b1 >= 0) {
				int updatedBal1 = b1 + dies.getAmount();
				p1.setBalance(updatedBal1);
				session.createQuery("update Player1 set balance=:newbal where id=123")
						.setParameter("newbal", updatedBal1).executeUpdate();
				session.save(dies);
				return Integer.toString(updatedBal1);
			} else {
				return "0 Player2 won the Game";
			}
		} else if (dies.getPlayer() == 321) {
			Player2 p2 = session.get(Player2.class, dies.getPlayer());
			int b2 = p2.getBalance();

			if (b2 >= 0) {
				int updatedBal2 = b2 + dies.getAmount();
				p2.setBalance(updatedBal2);
				session.createQuery("update Player2 set balance=:newbal where id=321")
						.setParameter("newbal", updatedBal2).executeUpdate();
				session.save(dies);
				return Integer.toString(updatedBal2);

			} else {
				return "0 Player1 won the Game";
			}
		}
		return "Invalide Input";
		}else {
			Player1 p1 = session.get(Player1.class, 123);
			Player2 p2 = session.get(Player2.class, 123);
			if (p1.getBalance()>p2.getBalance()) {
				return "Game Over 50 turns exceed Player1 won the Game";
			}else {
			return "Game Over 50 turns exceed Player2 won the Game";
			}
		}
	}

	public String rentPaid(Dies dies) {

		Session session = entityManager.unwrap(Session.class);
		List<Dies> d = session.createQuery("from Dies", Dies.class).getResultList();

		if (!(d.get(0).getId() > 49)) {
		
		if (dies.getPlayer() == 123) {
			Player1 p1 = session.get(Player1.class, dies.getPlayer());
			int b1 = p1.getBalance();
			
			Player2 p2 = session.get(Player2.class, 321);
			int b2 = p2.getBalance();
			
			if (b1 >= 0) {
				int updatedBal1 = b1 + dies.getAmount();
				int updatedBal2 = b2-dies.getAmount();
				p1.setBalance(updatedBal1);
				session.createQuery("update Player1 set balance=:newbal where id=123")
						.setParameter("newbal", updatedBal1).executeUpdate();
				session.createQuery("update Player2 set balance=:newbal where id=321")
				.setParameter("newbal", updatedBal2).executeUpdate();
				session.save(dies);
				return Integer.toString(updatedBal1);
			} else {
				return "0 Player2 won the Game";
			}
		} else if (dies.getPlayer() == 321) {
			Player2 p2 = session.get(Player2.class, dies.getPlayer());
			int b2 = p2.getBalance();

			Player1 p1 = session.get(Player1.class, dies.getPlayer());
			int b1 = p1.getBalance();
			
			if (b2 >= 0) {
				int updatedBal2 = b2 + dies.getAmount();
				int updatedBal1 = b1 - dies.getAmount();
				p2.setBalance(updatedBal2);
				session.createQuery("update Player2 set balance=:newbal where id=321")
						.setParameter("newbal", updatedBal2).executeUpdate();
				session.createQuery("update Player1 set balance=:newbal where id=123")
				.setParameter("newbal", updatedBal1).executeUpdate();
				session.save(dies);
				return Integer.toString(updatedBal2);

			} else {
				return "0 Player1 won the Game";
			}
		}
		return "Invalide Input";
	}else {
		Player1 p1 = session.get(Player1.class, 123);
		Player2 p2 = session.get(Player2.class, 123);
		if (p1.getBalance()>p2.getBalance()) {
			return "Game Over 50 turns exceed Player1 won the Game";
		}else {
		return "Game Over 50 turns exceed Player2 won the Game";
		}
	}

	}

	public String restart() {
		Session session = entityManager.unwrap(Session.class);
		session.createQuery("update Player1 set balance=:bal where id=123").setParameter("bal", 1000).executeUpdate();
		session.createQuery("update Player2 set balance=:bal where id=321").setParameter("bal", 1000).executeUpdate();
		
		return getBalance();
	}
}
