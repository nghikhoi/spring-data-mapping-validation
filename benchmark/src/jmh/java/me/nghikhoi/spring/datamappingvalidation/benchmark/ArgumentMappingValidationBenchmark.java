package me.nghikhoi.spring.datamappingvalidation.benchmark;

import me.nghikhoi.spring.datamappingvalidation.SpringDataMappingValidationApplication;
import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMappingValidation;
import me.nghikhoi.spring.datamappingvalidation.domain.args.DummyArgs;
import org.openjdk.jmh.annotations.*;
import org.springframework.context.ApplicationContext;

@BenchmarkMode(Mode.All)
@State(Scope.Benchmark)
public class ArgumentMappingValidationBenchmark {

    private ArgumentMappingValidation<DummyRequest, DummyArgs> dummyRequestValidation;
    private ArgumentMappingValidation<DummyRequest, DummyArgs> simpleDummyRequestValidation;
    private DummyRequest dummyRequest;

    @Setup
    public void setup() {
        dummyRequest = new DummyRequest(
                "dummyString",
                1,
                1.0,
                1L,
                1.0f,
                true,
                "alphaString",
                2,
                2.0,
                2L,
                2.0f,
                false,
                "betaString",
                3,
                3.0,
                3L,
                3.0f,
                true
        );
        ApplicationContext context = SpringDataMappingValidationApplication.run();
        dummyRequestValidation = context.getBean("dummyRequestValidation", ArgumentMappingValidation.class);
        simpleDummyRequestValidation = context.getBean("simpleDummyRequestValidation", ArgumentMappingValidation.class);
    }

    @Benchmark
    public void autoGenerateHandle() {
        DummyArgs args = dummyRequestValidation.validateAndMap(dummyRequest).get();
    }

    @Benchmark
    public void defaultHandle() {
        DummyArgs args = simpleDummyRequestValidation.validateAndMap(dummyRequest).get();
    }

}
