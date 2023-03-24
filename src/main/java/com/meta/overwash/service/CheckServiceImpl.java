package com.meta.overwash.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.mapper.CheckMapper;

@Service
public class CheckServiceImpl implements CheckService {

	@Autowired
	CheckMapper checkMapper;

	@Override
	public List<CheckDTO> getCheckList(Long confirmId) {
		CheckDTO checkDto = new CheckDTO();
		ReservationConfirmedDTO rcDto = new ReservationConfirmedDTO();
		rcDto.setConfirmId(confirmId);
		checkDto.setConfirm(rcDto);

		return checkMapper.getListByConfirmId(rcDto);
	}

}
