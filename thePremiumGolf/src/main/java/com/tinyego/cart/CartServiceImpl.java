package com.tinyego.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	CartServiceImpl() {
		System.out.println("===> CartServiceImpl()");
	}
	
	@Autowired
	CartDao dao;

	@Override
	public void insert(CartVo vo) {
		dao.insert(vo);
	}

	@Override
	public List<CartVo> edit(CartVo vo) {
		return dao.edit(vo);
	}

	@Override
	public void delete(CartVo vo) {
		dao.delete(vo);
	}
}
