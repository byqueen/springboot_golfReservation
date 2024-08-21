package com.tinyego.cart;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao {
	void insert(CartVo vo);
	CartVo edit(CartVo vo);
	void delete(CartVo vo);
}
