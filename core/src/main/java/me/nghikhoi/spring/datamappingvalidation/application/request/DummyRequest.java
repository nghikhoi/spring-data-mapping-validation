package me.nghikhoi.spring.datamappingvalidation.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyRequest {

    private String dummyString;
    private int dummyInt;
    private double dummyDouble;
    private long dummyLong;
    private float dummyFloat;
    private boolean dummyBoolean;

    private String alphaString;
    private int alphaInt;
    private double alphaDouble;
    private long alphaLong;
    private float alphaFloat;
    private boolean alphaBoolean;

    private String betaString;
    private int betaInt;
    private double betaDouble;
    private long betaLong;
    private float betaFloat;
    private boolean betaBoolean;

}
