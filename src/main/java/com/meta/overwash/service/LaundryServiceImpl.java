package com.meta.overwash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import com.meta.overwash.mapper.LaundryMapper;

import lombok.extern.log4j.Log4j;

@Service
public class LaundryServiceImpl implements LaundryService {

	@Autowired
	LaundryMapper laundryMapper;

	@Override
	public List<LaundryDTO> getList() {

		return laundryMapper.getList();
	}

	@Override
	public WashingCompleteDTO updateWashComplete() {

		return null;
	}

}
