package com.meta.overwash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.PagenationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import com.meta.overwash.mapper.LaundryMapper;

@Service
public class LaundryServiceImpl implements LaundryService {

	@Autowired
	LaundryMapper laundryMapper;

	@Override
	public List<LaundryDTO> getList() {

		return laundryMapper.getList();
	}

	@Override
	public Map<String, Object> getListPaging(Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		map.put("laundryPaging", new PagenationDTO(cri, getLaundryCnt()));
		map.put("laundries", laundryMapper.getListPaging(cri));
		return map;
	}

	@Override
	public WashingCompleteDTO updateWashComplete() {

		return null;
	}

	// 페이징 처리용 메소드
	private int getLaundryCnt() {
		return laundryMapper.getLaundryCnt().intValue();
	}

}
