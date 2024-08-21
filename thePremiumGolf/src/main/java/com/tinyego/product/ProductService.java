package com.tinyego.product;

import java.util.List;

public interface ProductService {
	List<ProductVo> list(ProductVo vo);
	ProductVo edit(ProductVo vo);
	void update(ProductVo vo);
	void insert(ProductVo vo);
	void delete(ProductVo vo);
	
	int count(ProductVo vo);
}
