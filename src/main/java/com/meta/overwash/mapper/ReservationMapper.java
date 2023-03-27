package com.meta.overwash.mapper;

import java.util.HashMap;
import java.util.List;

import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	public List<ReservationDTO> getList();

	public List<ReservationDTO> getListEach(Long memberId);

	public ReservationDTO getListEachOne(ReservationDTO reservation);

	public void insertReservation(ReservationDTO reservation);

	public List<ReservationDTO> getListByMember(HashMap<String, Object> map);

	public int updateReservation(ReservationDTO reservation);

	public int deleteReservation(Long reservationId);	
	
	public void insertWashingComplete(WashingCompleteDTO washingComplete);

	public int updateReservationStatus(ReservationDTO reservation);
		 
	public ReservationDTO getReservation(Long reservationId);

	// 고객의 예약 갯수 (페이징처리용)
	public Long getCountToMember(Long memberId);
	
}  




