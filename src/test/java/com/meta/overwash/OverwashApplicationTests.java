package com.meta.overwash;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.service.CrewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OverwashApplicationTests {

    @Autowired
    private CrewService crewService;
    @Test
    void contextLoads() {
    }

    @Test
    public void getTotalTest() throws Exception{

        String table = "users";
        Criteria cri = new Criteria(1, 5);

        System.out.println(crewService.getTotal(table, cri) + "11111111111111111111111111111111111111");
    }

}
