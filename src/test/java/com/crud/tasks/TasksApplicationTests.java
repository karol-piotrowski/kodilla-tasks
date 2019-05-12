package com.crud.tasks;

import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

    @Autowired
    private DbService dbService = new DbService();


    @Test
    public void contextLoads() {
    }


}

