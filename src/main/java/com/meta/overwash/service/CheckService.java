package com.meta.overwash.service;

import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.CheckDTO;

public interface CheckService {

	public List<CheckDTO> getCheckList(Long confirmId);
}
