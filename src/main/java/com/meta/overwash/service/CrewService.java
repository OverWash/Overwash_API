package com.meta.overwash.service;

import java.util.List;

import com.meta.overwash.domain.*;

public interface CrewService {

	public void insert(UserDTO userDTO, CrewDTO crewDTO) throws Exception;

//	public boolean remove(Long crewId) throws Exception;

	public boolean modify(UserDTO user, CrewDTO crewDTO) throws Exception;

	public CrewDTO getCrew(Long userId) throws Exception;

	public List<CrewDTO> getCrewList(String role) throws Exception;

	public boolean checkPw(UserDTO user) throws Exception;

	public String getContact(String contact) throws Exception;

	public List<ReservationDTO> getToBeCollectList() throws Exception;

	public List<ReservationDTO> getToBeCollectListLimit() throws Exception;

	public List<ReservationDTO> getToBeCollectListWithPaging(Criteria cri) throws Exception;

	public List<WashingCompleteDTO> getWcList() throws Exception;

	public List<WashingCompleteDTO> getWcListLimit() throws Exception;

	public List<WashingCompleteDTO> getWcListWithPaging(Criteria cri) throws Exception;

	public boolean updateDelivering(Long reservationId, DeliveryDTO deliveryDTO) throws Exception;

	public boolean updateResDoneDelivery(Long reservationId, Long deliveryId) throws Exception;

	public List<DeliveryDTO> getDeliveryList(Long crewId, String status) throws Exception;

	public List<DeliveryDTO> getDeliveryListWithPaging(Long crewId, String status, Criteria cri) throws Exception;

	public int getTotalToBeCollect(Criteria cri) throws Exception;

	public int getTotalToBeDelivery(Criteria cri) throws Exception;

}
