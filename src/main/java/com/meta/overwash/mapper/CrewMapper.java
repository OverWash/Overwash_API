package com.meta.overwash.mapper;

import java.util.List;
import java.util.Map;

import com.meta.overwash.domain.CrewDTO;
import com.meta.overwash.domain.DeliveryDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.domain.WashingCompleteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrewMapper {

	public void insertCrew(CrewDTO crewDTO) throws Exception;

	public List<CrewDTO> getCrewList(String role) throws Exception;

	public CrewDTO getCrew(Long userId) throws Exception;

	public int updateCrew(CrewDTO crew) throws Exception;

	public int deleteCrew(Long crewId) throws Exception;

	public String getCrewContact(String contact) throws Exception;

	public List<ReservationDTO> selectToBeCollectList() throws Exception;

	public List<WashingCompleteDTO> selectWcList() throws Exception;

	public int updateDelivering(Long reservationId) throws Exception;

	public int updateResStatusDeliverDone(Long reservationId) throws Exception;

	public int updateDoneDelivery(Long deliveryId) throws Exception;

	public int insertDelivery(DeliveryDTO deliveryDTO) throws Exception;

	public List<DeliveryDTO> selectDeliveryList(Map<String, Object> paramMap) throws Exception;
}
