package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
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

    @Test
    public void testGetTask1() {
        //Given
        //When
        Task taskOne = dbService.getTaskById(1l);
        //Then
        Assert.assertEquals((long)1, (long)taskOne.getId());
        Assert.assertEquals("test", taskOne.getTitle());
        Assert.assertEquals("test1", taskOne.getContent());
    }

}

