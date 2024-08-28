package com.tinyego.cart;

import java.util.List;

public interface CartService {
	void insert(CartVo vo);
	List<CartVo> edit(CartVo vo);
	void delete(CartVo vo);
}
