package com.example.studentapp.app_interface;

import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;

public interface IClickBtnRating {
    void rateClass (ClassObject classObject);
    void seeRateDetail (Rate rate);
}
