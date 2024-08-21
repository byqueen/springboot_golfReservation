package com.tinyego.cart;

public interface CartService {
	void insert(CartVo vo);
	CartVo edit(CartVo vo);
	void delete(CartVo vo);
}
