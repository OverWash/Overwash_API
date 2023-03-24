package com.meta.overwash.service;

import java.util.List;

import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.WashingCompleteDTO;

public interface LaundryService {
	public List<LaundryDTO> getList();

	public WashingCompleteDTO updateWashComplete();
}