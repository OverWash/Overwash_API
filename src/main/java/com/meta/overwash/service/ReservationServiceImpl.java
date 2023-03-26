package com.meta.overwash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.PagenationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import com.meta.overwash.mapper.ReservationMapper;

import lombok.extern.log4j.Log4j;


@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationMapper mapper;

	@Override
	public List<ReservationDTO> getList() {
		return mapper.getList();
	}

	@Override
	public List<ReservationDTO> getListEach(Long memberId) {
		return mapper.getListEach(memberId);
	}

	@Override
	public ReservationDTO getListEachOne(ReservationDTO reservation) {
		return mapper.getListEachOne(reservation);
	}

	@Override
	public void insert(ReservationDTO reservation) {
		mapper.insertReservation(reservation);
	}

	@Override
	public void updateReservationStatus(ReservationDTO reservation) {
		mapper.updateReservationStatus(reservation);
	}

	@Override
	@Transactional
	public void insertWashingComplete(WashingCompleteDTO washComplete) {
		mapper.insertWashingComplete(washComplete);
		ReservationDTO reservation = washComplete.getConfirm().getReservation();
		reservation.setReservationStatus("세탁완료");
		updateReservationStatus(reservation);
	}

	@Override
	public Map<String, Object> getListByMember(Criteria cri, Long memberId) {

		// Mapper에 들어갈 파라미터 map으로 변환
		HashMap<String, Object> vo = new HashMap<String, Object>();
		vo.put("pageNum", cri.getPageNum());
		vo.put("amount", cri.getAmount());
		vo.put("memberId", memberId);

		Map<String, Object> map = new HashMap<>();
		map.put("reservationPaging", new PagenationDTO(cri, getCountToMember(memberId).intValue()));
		map.put("reservations", mapper.getListByMember(vo));
		return map;
	}

	/* ------------------- paging 메소드 ------------------  */

	private Long getCountToMember(Long memberId) {
		return mapper.getCountToMember(memberId);
	}

}
