package com.meta.overwash.mapper;

import java.util.HashMap;
import java.util.List;

import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationConfirmedMapper {

	public List<ReservationConfirmedDTO> getList(HashMap<String,Object> hash);

	public void insertReservationConfirm(ReservationConfirmedDTO rc);

	// 예약 확정 번호로 예약 확정 정보와 예약 정보도 깉이 가져온다
	public ReservationConfirmedDTO getReservationConfirm(Long confirmId);

//	public Long updateReservationConfirm(ReservationConfirmedDTO rc);
//
//	public Long deleteReservationConfirm(Long confirmId);

	public Long getCount(UserDTO users);

	//관리자 세탁예정목록 가져오기(결제가 완료된)
	public List<ReservationConfirmedDTO> getListByPaymentComplete();



}
