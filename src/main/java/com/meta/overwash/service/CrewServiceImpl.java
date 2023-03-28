package com.meta.overwash.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.meta.overwash.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.overwash.mapper.CrewMapper;
import com.meta.overwash.mapper.UserMapper;

@Service
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewMapper crewMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public void insert(UserDTO user, CrewDTO crew) throws Exception {

//		user.setRole("ROLE_CREW");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userMapper.insertUser(user);

		crew.setUser(user);

		crewMapper.insertCrew(crew);
	}

//	@Override
//	public boolean remove(Long crewId) throws Exception {
//		return crewMapper.deleteCrew(crewId) == 1;
//	}

	@Override
	@Transactional
	public boolean modify(UserDTO user, CrewDTO crew) throws Exception {

		System.out.println(user);
		System.out.println(crew);
		if (user.getPassword() != null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			if(userMapper.updateUser(user) == 0) return false;
		}

		return crewMapper.updateCrew(crew) == 1;
	}

	@Override
	public CrewDTO getCrew(Long userId) throws Exception {
		return crewMapper.getCrew(userId);
	}

	@Override
	public String getCrewName(Long userId) throws Exception {
		return crewMapper.selectCrewName(userId);
	}

	@Override
	public CrewDTO getCrewInfo(Long userId) throws Exception {

		CrewDTO crew = crewMapper.getCrew(userId);
//		UserDTO user = new UserDTO();

		crew.setUser(userService.getUserById(userId));


		return crew;
	}

	public List<CrewDTO> getCrewList(String role) throws Exception {
		return crewMapper.getCrewList(role);
	}

	@Override
	public boolean checkPw(UserDTO user) throws Exception {
		UserDTO userInfo = userMapper.getUser(user.getEmail());

		if (userInfo != null) {
			if (bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
				return true;
			}
		}
		return false;

	}
	public String getContact(String contact) throws Exception {
		return crewMapper.getCrewContact(contact);
	}

	@Override
	public List<ReservationDTO> getToBeCollectList() throws Exception {
		return crewMapper.selectToBeCollectList();
	}

	@Override
	public List<ReservationDTO> getToBeCollectListLimit() throws Exception {
		return crewMapper.selectToBeCollectListLimit();
	}

	@Override
	public List<ReservationDTO> getToBeCollectListWithPaging(Criteria cri) throws Exception {
		return crewMapper.selectToBeCollectListWithPaging(cri);
	}

	@Override
	public List<WashingCompleteDTO> getWcList() throws Exception {
		return crewMapper.selectWcList();
	}

	@Override
	public List<WashingCompleteDTO> getWcListLimit() throws Exception {
		return crewMapper.selectWcListLimit();
	}

	@Override
	public List<WashingCompleteDTO> getWcListWithPaging(Criteria cri) throws Exception {
		return crewMapper.selectWcListWithPaging(cri);
	}

	@Override
	@Transactional
	public boolean updateDelivering(Long reservationId, DeliveryDTO deliveryDTO) throws Exception {
		crewMapper.updateDelivering(reservationId);
		return crewMapper.insertDelivery(deliveryDTO) == 1;
	}

	@Override
	@Transactional
	public boolean updateResDoneDelivery(Long reservationId, Long deliveryId) throws Exception {
		crewMapper.updateResStatusDeliverDone(reservationId);
		return crewMapper.updateDoneDelivery(deliveryId) == 1;
	}

	@Override
	public List<DeliveryDTO> getDeliveryList(Long crewId, String status) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crewId", crewId);
		paramMap.put("status", status);

		return crewMapper.selectDeliveryList(paramMap);
	}

	@Override
	public List<DeliveryDTO> getDeliveryListWithPaging(Long crewId, String status, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("crewId", crewId);
		paramMap.put("status", status);
		paramMap.put("pageNum", cri.getPageNum());
		paramMap.put("amount", cri.getAmount());

		return crewMapper.selectDeliveryListWithPaging(paramMap);
	}

	@Override
	public int getTotalToBeCollect(Criteria cri) throws Exception {
		return crewMapper.selectTotalToBeCollect(cri);
	}

	@Override
	public int getTotalToBeDelivery(Criteria cri) throws Exception {
		return crewMapper.selectTotalToBeDelivery(cri);
	}


}




















