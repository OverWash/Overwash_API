package com.meta.overwash.service;

import java.util.List;

import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.WashingCompleteDTO;

public interface ReservationService {
	
	public List<ReservationDTO> getList();

	public List<ReservationDTO> getListEach(Long memberId);

	public ReservationDTO getListEachOne(ReservationDTO reservation);

	public List<ReservationDTO> getListByMember(Long memberId);

	// 예약 신청
	public void insert(ReservationDTO reservation);

	// 예약 상태 변경
	public void updateReservationStatus(ReservationDTO reservation);

	// 세탁 완료에 추가 ?
	public void insertWashingComplete(WashingCompleteDTO washComplete);


}
