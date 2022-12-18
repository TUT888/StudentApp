package com.example.studentapp.app_interface;

import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;

public interface IClickBtnRating {
    void rateClass(ClassObject classObject, int adapterPosition);
    void seeRateDetail (Rate rate);
}
