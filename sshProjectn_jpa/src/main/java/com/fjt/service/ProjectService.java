package com.fjt.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {

	public void reportUp(MultipartFile file);
	
	public Map<String, Object> serch(Pageable pageb,Map<String, Object> map);
}
