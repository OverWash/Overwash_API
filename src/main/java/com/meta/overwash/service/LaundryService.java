package com.meta.overwash.service;

import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.WashingCompleteDTO;

public interface LaundryService {
	public List<LaundryDTO> getList();

	// 가격표 페이징
	public Map<String, Object> getListPaging(Criteria cri);

	public WashingCompleteDTO updateWashComplete();
}