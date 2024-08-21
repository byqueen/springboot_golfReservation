package com.tinyego.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tinyego.golf.GolfVo;

@Mapper
public interface ProductDao {
	List<ProductVo> list(ProductVo vo);
	ProductVo edit(ProductVo vo);
	void update(ProductVo vo);
	void insert(ProductVo vo);
	void delete(ProductVo vo);
	
	int count(ProductVo vo);
}
