package com.meta.overwash.service;

import com.meta.overwash.mapper.ReservationConfirmedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import com.meta.overwash.mapper.ReservationMapper;
import com.meta.overwash.mapper.WashingCompleteMapper;

@Service
public class WashingCompleteServiceImpl implements WashingCompleteService {

	@Autowired
	WashingCompleteMapper wcMapper;

	@Autowired
	ReservationMapper reservationMapper;

	@Autowired
	ReservationConfirmedMapper reservationConfirmedMapper;

	@Override
	public void insertWashingComplete(Long reservationConfirmedId) {
		ReservationConfirmedDTO rcDto= reservationConfirmedMapper.getReservationConfirm(reservationConfirmedId);
		rcDto.getReservation().setReservationStatus("세탁완료");
		WashingCompleteDTO wcDto = new WashingCompleteDTO();
		wcDto.setConfirm(rcDto);
		wcMapper.insertWashingComplete(wcDto);
		reservationMapper.updateReservationStatus(rcDto.getReservation());
	}

}
