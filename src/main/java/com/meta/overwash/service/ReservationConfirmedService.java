package com.meta.overwash.service;

import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.CrewDTO;
import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.ReservationConfirmedDTO;

public interface ReservationConfirmedService {

	public Map<String, Object> getList(Criteria cri);

	public List<ReservationConfirmedDTO> getListToPaymentCompleteList();
	public ReservationConfirmedDTO insertReservationConfirmed(Long reservationId, CrewDTO crew);
}
