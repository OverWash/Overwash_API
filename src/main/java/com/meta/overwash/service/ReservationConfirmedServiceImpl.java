package com.meta.overwash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.overwash.domain.CrewDTO;
import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.LaundryFactoryDTO;
import com.meta.overwash.domain.PagenationDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.mapper.LaundryFactoryMapper;
import com.meta.overwash.mapper.ReservationConfirmedMapper;
import com.meta.overwash.mapper.ReservationMapper;

@Service
public class ReservationConfirmedServiceImpl implements ReservationConfirmedService {

	@Autowired
	ReservationConfirmedMapper rcMapper;

	@Autowired
	LaundryFactoryMapper factoryMapper;

	@Autowired
	ReservationMapper resMapper;
	@Override
	@Transactional
	// 크루가 수거하기 버튼 클릭 시 동작하는 서비스
	public ReservationConfirmedDTO insertReservationConfirmed(Long reservationId, CrewDTO crew) {
		System.out.println(crew);
		ReservationConfirmedDTO rcDto = new ReservationConfirmedDTO();
		rcDto.setCrew(crew);
		rcDto.setReservation(resMapper.getReservation(reservationId));
		System.out.println(rcDto);
		String memberAddress = rcDto.getReservation().getMember().getMemberAddress().split(" ")[1];

		LaundryFactoryDTO factory = new LaundryFactoryDTO();
		factory.setFactoryAddress(memberAddress);
		factory = factoryMapper.getLaundryFactoryByAddr(factory);
		if (factory.getFactoryId() == null) {
			return null; // exception으로 넘길 예정
		}
		rcDto.setFactory(factory);
		rcMapper.insertReservationConfirm(rcDto);
		rcDto.getReservation().setReservationStatus("예약확정");
		resMapper.updateReservationStatus(rcDto.getReservation());
		return rcDto;
	}

	// 검수예정내역(예약확정)을 조회
	@Override
	@Transactional
	public Map<String, Object> getList(Criteria cri) {
		// Member와 관리자가 같은 Mapper를 사용하기에 if문으로 UserId가 null인지 아닌지로 처리하기 때문에
		// 일단 HashMap으로 받기로 결정
		HashMap<String, Object> vo = new HashMap<String, Object>();
		vo.put("pageNum", cri.getPageNum());
		vo.put("amount", cri.getAmount());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reservationConfirmedPaging", new PagenationDTO(cri, rcMapper.getCount(new UserDTO()).intValue()));
		map.put("reservationConfirmeds", rcMapper.getList(vo));
		return map;
	}


	// 결제완료내역을 가져오는 리스트
	@Override
	public List<ReservationConfirmedDTO> getListToPaymentCompleteList() {

		return rcMapper.getListByPaymentComplete();

	}

}
