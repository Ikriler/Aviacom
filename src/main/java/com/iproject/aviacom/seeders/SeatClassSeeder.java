package com.iproject.aviacom.seeders;

import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.models.SeatClass;
import com.iproject.aviacom.repositories.LayoutTypeRepository;
import com.iproject.aviacom.repositories.SeatClassRepository;

import java.util.ArrayList;
import java.util.List;

public class SeatClassSeeder {
    private static List<SeatClass> seatClassList = new ArrayList<>();

    private static void init() {
        seatClassList.add(new SeatClass("Эконом", 0.0));
        seatClassList.add(new SeatClass("Бизнес", 25.0));
        seatClassList.add(new SeatClass("Первый", 50.0));
    }

    public static void seed(SeatClassRepository seatClassRepository) {
        init();
        for (SeatClass seatClass : seatClassList) {
            if (seatClassRepository.findByName(seatClass.getName()) == null) {
                seatClassRepository.save(seatClass);
            }
        }
    }
}
