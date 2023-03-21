package me.nghikhoi.spring.datamappingvalidation.application.validation;

import io.vavr.control.Either;
import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.common.utils.ValidateUtils;
import me.nghikhoi.spring.datamappingvalidation.domain.args.DummyArgs;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMappingValidation;
import org.springframework.stereotype.Component;

@Component("simpleDummyRequestValidation")
public class DummyRequestValidation implements ArgumentMappingValidation<DummyRequest, DummyArgs> {

    @Override
    public Either<Throwable, DummyArgs> validateAndMap(DummyRequest from) {
        try {
            if (!validate(from)) {
                throw new IllegalArgumentException("Invalid request");
            }
            DummyArgs args = toArgs(from);
            return Either.right(args);
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    /**
     * @Pattern(regexp = "^[a-zA-Z0-9]*$")
     *     private String dummyString;
     *     @Max(100)
     *     private int dummyInt;
     *     @Max(100)
     *     private double dummyDouble;
     *     @Max(100)
     *     private long dummyLong;
     *     @Max(100)
     *     private float dummyFloat;
     *     @AssertTrue
     *     private boolean dummyBoolean;

     */
    private boolean validate(DummyRequest from) {
        if (!from.getDummyString().matches("^[a-zA-Z0-9]*$")) {
            return false;
        }
        if (from.getDummyInt() > 100) {
            return false;
        }
        if (from.getDummyDouble() > 100) {
            return false;
        }
        if (from.getDummyLong() > 100) {
            return false;
        }
        if (from.getDummyFloat() > 100) {
            return false;
        }
        if (!from.isDummyBoolean()) {
            return false;
        }

        if (!from.getAlphaString().matches("^[a-zA-Z0-9]*$")) {
            return false;
        }
        if (from.getAlphaInt() > 100) {
            return false;
        }
        if (from.getAlphaDouble() > 100) {
            return false;
        }
        if (from.getAlphaLong() > 100) {
            return false;
        }
        if (from.getAlphaFloat() > 100) {
            return false;
        }
        if (from.isAlphaBoolean()) {
            return false;
        }

        if (!from.getBetaString().matches("^[a-zA-Z0-9]*$")) {
            return false;
        }
        if (from.getBetaInt() > 100) {
            return false;
        }
        if (from.getBetaDouble() > 100) {
            return false;
        }
        if (from.getBetaLong() > 100) {
            return false;
        }
        if (from.getBetaFloat() > 100) {
            return false;
        }
        if (!from.isBetaBoolean()) {
            return false;
        }
        return true;
    }

    private DummyArgs toArgs(DummyRequest from) {
        return new DummyArgs(
                from.getDummyString(),
                from.getDummyInt(),
                from.getDummyDouble(),
                from.getDummyLong(),
                from.getDummyFloat(),
                from.isDummyBoolean(),

                from.getAlphaString(),
                from.getAlphaInt(),
                from.getAlphaDouble(),
                from.getAlphaLong(),
                from.getAlphaFloat(),
                from.isAlphaBoolean(),

                from.getBetaString(),
                from.getBetaInt(),
                from.getBetaDouble(),
                from.getBetaLong(),
                from.getBetaFloat(),
                from.isBetaBoolean()
        );
    }

}
