package me.nghikhoi.spring.datamappingvalidation.application.handler;

import lombok.RequiredArgsConstructor;
import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.domain.args.DummyArgs;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMappingValidation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyHandler {

    private final ArgumentMappingValidation<DummyRequest, DummyArgs> dummyRequestValidation;

    public void handle(DummyRequest request) {
        DummyArgs args = dummyRequestValidation.validateAndMap(request).get();
        System.out.println("DummyArgs: " + args.getDummyString());
    }

}
