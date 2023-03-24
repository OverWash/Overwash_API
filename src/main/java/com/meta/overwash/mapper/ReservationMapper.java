package com.meta.overwash.mapper;

import java.util.List;

import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	public List<ReservationDTO> getList();

	public List<ReservationDTO> getListEach(Long memberId);

	public ReservationDTO getListEachOne(ReservationDTO reservation);

	public void insertReservation(ReservationDTO reservation);

	public List<ReservationDTO> getListByMember(Long memberId);

	public int updateReservationRequest(ReservationDTO reservation);

	public int deleteReservation(Long reservationId);	
	
	public void insertWashingComplete(WashingCompleteDTO washingComplete);

	public int updateReservationStatus(ReservationDTO reservation);
		 
	public ReservationDTO getReservation(Long reservationId);

}  




