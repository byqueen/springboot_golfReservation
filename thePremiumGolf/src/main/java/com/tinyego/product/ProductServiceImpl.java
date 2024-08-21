package com.tinyego.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	ProductServiceImpl() {
		System.out.println("===> ProductServiceImpl()");
	}
	
	@Autowired
	private ProductDao dao;

	@Override
	public List<ProductVo> list(ProductVo vo) {
		return dao.list(vo);
	}

	@Override
	public ProductVo edit(ProductVo vo) {
		return dao.edit(vo);
	}

	@Override
	public void update(ProductVo vo) {
		dao.update(vo);
	}

	@Override
	public void insert(ProductVo vo) {
		dao.insert(vo);
	}

	@Override
	public void delete(ProductVo vo) {
		dao.delete(vo);
	}

	@Override
	public int count(ProductVo vo) {
		return dao.count(vo);
	}
	
}
