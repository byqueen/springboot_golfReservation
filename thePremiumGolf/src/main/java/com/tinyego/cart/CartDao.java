package com.tinyego.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao {
	void insert(CartVo vo);
	List<CartVo> edit(CartVo vo);
	void delete(CartVo vo);
}
