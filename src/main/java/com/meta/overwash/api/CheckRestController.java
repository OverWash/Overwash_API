package com.meta.overwash.api;

import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckRestController {

    @Autowired
    CheckService checkService;

    // 검수 리스트를 json 으로 전송
	@GetMapping(value = "/check/{confirmId}")
	public ResponseEntity<List<CheckDTO>> getCheckList(@PathVariable("confirmId") Long confirmId) {
		return new ResponseEntity<List<CheckDTO>>(checkService.getCheckList(confirmId), HttpStatus.OK);
	}
}
