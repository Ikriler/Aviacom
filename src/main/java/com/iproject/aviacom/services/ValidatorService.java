package com.iproject.aviacom.services;

import org.springframework.ui.Model;

public class ValidatorService {
    public static boolean notLessZero(Model model, Double value, String fieldName){
        if(value < 0) {
            model.addAttribute(fieldName, "Значние не должно быть меньше нуля");
            return false;
        }
        return true;
    }
}
