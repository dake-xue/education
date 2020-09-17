package com.zhongsheng.education;

import com.zhongsheng.education.service.UserService;
import com.zhongsheng.education.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducationApplicationTests {

    @Test
    void contextLoads() {

        UserService userService = new UserServiceImpl();
        System.out.println(userService.test());
    }

}
