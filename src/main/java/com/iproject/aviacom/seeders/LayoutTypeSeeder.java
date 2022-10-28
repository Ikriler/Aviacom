package com.iproject.aviacom.seeders;

import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.repositories.LayoutTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class LayoutTypeSeeder {
    private static List<LayoutType> layoutTypeList = new ArrayList<>();

    private static void init() {
        layoutTypeList.add(new LayoutType("Малая"));
        layoutTypeList.add(new LayoutType("Средняя"));
        layoutTypeList.add(new LayoutType("Большая"));
    }

    public static void seed(LayoutTypeRepository layoutTypeRepository) {
        init();
        for (LayoutType layoutType : layoutTypeList) {
            if (layoutTypeRepository.findByName(layoutType.getName()) == null) {
                layoutTypeRepository.save(layoutType);
            }
        }
    }

}
