package com.nnk.springboot.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nnk.springboot.dao.BidListDao;
import com.nnk.springboot.dao.UserDao;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;

@Component
public class DataInitializr {

	@Autowired
	UserDao userDao;

	@Autowired
	BidListDao bidListDao;

//	@Autowired
//	CurvePointDao curvePointDao;
//
//	@Autowired
//	RatingDao ratingDao;
//
//	@Autowired
//	RuleNameDao ruleNameDao;
//
//	@Autowired
//	TradeDao tradeDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void initUser() {
		User existingUser = userDao.findByUsername("testu");

		if (existingUser == null) {
			User user = new User();
			user.setId(1);
			user.setUsername("testu");
			user.setFullname("testfn");
			user.setRole("USER");

			user.setPassword(passwordEncoder.encode("testp"));

			userDao.save(user);
		}
	}

	@PostConstruct
	public void initBidList() {
		BidList existingBidList = bidListDao.findByAccount("BidList Account");

		if (existingBidList == null) {
			BidList bidList = new BidList();
			bidList.setBidListId(1);
			bidList.setAccount("BidList Account");
			bidList.setType("BidList Type");
			bidList.setBidQuantity(1.0);

			bidListDao.save(bidList);
		}
	}
}
