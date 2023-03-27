package com.meta.overwash.service;

import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.domain.Criteria;

public interface CheckService {

	public List<CheckDTO> getCheckList(Long confirmId);

	public Map<String, Object> getCheckListPaging(Criteria cri, Long confirmId);
}
