package me.nghikhoi.spring.datamappingvalidation.common;

import io.vavr.control.Either;

public interface ArgumentMappingValidation<F, T> {

    Either<Throwable, T> validateAndMap(F from);

}
