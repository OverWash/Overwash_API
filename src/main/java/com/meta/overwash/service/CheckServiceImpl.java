package com.meta.overwash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.PagenationDTO;
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

	@Override
	public Map<String, Object> getCheckListPaging(Criteria cri, Long confirmId) {
		// Mapper에 들어갈 파라미터 map으로 변환
		HashMap<String, Object> vo = new HashMap<String, Object>();
		vo.put("pageNum", cri.getPageNum());
		vo.put("amount", cri.getAmount());
		vo.put("confirmId", confirmId);

		Map<String, Object> map = new HashMap<>();
		map.put("checkPaging", new PagenationDTO(cri, getCountFromCheck(confirmId)));
		map.put("checks", checkMapper.getListPaging(vo));
		return map;
	}

	private int getCountFromCheck(Long confirmId) {
		return checkMapper.getCountFromCheck(confirmId).intValue();
	}

}
