package me.nghikhoi.spring.datamappingvalidation.application.validation;

import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.domain.args.DummyArgs;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMappingValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ValidationBean {

    @Bean
    public ArgumentMappingValidation<DummyRequest, DummyArgs> dummyRequestValidation() {
        return new DummyRequestValidation();
    }

}
