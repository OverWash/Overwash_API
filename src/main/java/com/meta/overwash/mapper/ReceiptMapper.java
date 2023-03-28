package com.meta.overwash.mapper;

import java.util.HashMap;
import java.util.List;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.ReceiptDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiptMapper {

	public void insertReceipt(ReceiptDTO receipt);

	// 영수증번호로 상세보기 가져옴
	public ReceiptDTO getReceipt(Long receiptId);

	// 회원이 보유한 영수증 리스트
	public List<ReceiptDTO> getReceiptList(Long userId);

	// 회원이 보유한 영수증 리스트 (페이징)
	public List<ReceiptDTO> getReceiptListPaging(HashMap<String, Object> hashMap);

	// 회원이 보유한 영수증 중 예약상태가 '배달완료'인 리스트
	public List<ReceiptDTO> getDeliveryCompletedList(Long userId);

	// 페이징 처리용
	public Long getCountReceipt(Long userId);

}
