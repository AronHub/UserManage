package com.fjt.repositery.custom;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjt.pojo.Emplye;

public interface EmplyCustom {

	public Page<Emplye> pageSerch(Pageable pageable,Map<String, Object> map,String jpql);
}
