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

	// 주문접수상태 예약건 삭제
	public void removeReservation(Long reservationId);

	// 주문접수상태 예약건 요청사항수정
	public void updateReservationRequest(ReservationDTO reservation);

}
