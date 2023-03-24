package com.meta.overwash.mapper;

import java.util.List;
import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckMapper {

	public List<CheckDTO> getList();

	public void insertCheck(CheckDTO check);

	public CheckDTO getCheck(Long checkId);

	public int updateCheck(CheckDTO check);

	public int deleteCheck(Long checkId);

	public List<CheckDTO> getListByConfirmId(ReservationConfirmedDTO rcDto);

}
