package com.iproject.aviacom.components;

import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.repositories.LayoutTypeRepository;
import com.iproject.aviacom.repositories.PostRepository;
import com.iproject.aviacom.repositories.SeatClassRepository;
import com.iproject.aviacom.seeders.EmployeeSeeder;
import com.iproject.aviacom.seeders.LayoutTypeSeeder;
import com.iproject.aviacom.seeders.PostSeeder;
import com.iproject.aviacom.seeders.SeatClassSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class updateListener {

    @Autowired
    LayoutTypeRepository layoutTypeRepository;
    @Autowired
    SeatClassRepository seatClassRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        LayoutTypeSeeder.seed(layoutTypeRepository);
        SeatClassSeeder.seed(seatClassRepository);
        PostSeeder.seed(postRepository);
        EmployeeSeeder.seed(employeeRepository, postRepository);
    }
}
