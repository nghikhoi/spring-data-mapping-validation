package me.nghikhoi.spring.datamappingvalidation.domain.args;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMapping;

@ArgumentMapping(fromClass = DummyRequest.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyArgs {

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String dummyString;
    @Max(100)
    private int dummyInt;
    @Max(100)
    private double dummyDouble;
    @Max(100)
    private long dummyLong;
    @Max(100)
    private float dummyFloat;
    @AssertTrue
    private boolean dummyBoolean;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String alphaString;
    @Max(100)
    private int alphaInt;
    @Max(100)
    private double alphaDouble;
    @Max(100)
    private long alphaLong;
    @Max(100)
    private float alphaFloat;
    @AssertFalse
    private boolean alphaBoolean;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String betaString;
    @Max(100)
    private int betaInt;
    @Max(100)
    private double betaDouble;
    @Max(100)
    private long betaLong;
    @Max(100)
    private float betaFloat;
    @AssertTrue
    private boolean betaBoolean;

}
