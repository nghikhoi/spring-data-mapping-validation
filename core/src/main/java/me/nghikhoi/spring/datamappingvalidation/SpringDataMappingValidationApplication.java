package me.nghikhoi.spring.datamappingvalidation;

import me.nghikhoi.spring.datamappingvalidation.application.request.DummyRequest;
import me.nghikhoi.spring.datamappingvalidation.common.ArgumentMappingValidation;
import me.nghikhoi.spring.datamappingvalidation.common.MappingValidationBeanInitializer;
import me.nghikhoi.spring.datamappingvalidation.domain.args.DummyArgs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringDataMappingValidationApplication {

    private static final DummyRequest DUMMY_REQUEST = new DummyRequest(
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

    public static void main(String[] args) {
        ApplicationContext context = run(args);

        ArgumentMappingValidation<DummyRequest, DummyArgs> dummyRequestValidation = context.getBean("dummyRequestValidation", ArgumentMappingValidation.class);
        ArgumentMappingValidation<DummyRequest, DummyArgs> simpleDummyRequestValidation = context.getBean("simpleDummyRequestValidation", ArgumentMappingValidation.class);

        List<Long> autoGenerateHandle = new LinkedList<>();
        List<Long> defaultHandle = new LinkedList<>();

        long start, end;
        DummyArgs result;

        for (int i = 0; i < 10000000; i++) {
            start = System.nanoTime();
            result = dummyRequestValidation.validateAndMap(DUMMY_REQUEST).get();
            end = System.nanoTime();
            autoGenerateHandle.add(end - start);

            start = System.nanoTime();
            result = simpleDummyRequestValidation.validateAndMap(DUMMY_REQUEST).get();
            end = System.nanoTime();
            defaultHandle.add(end - start);
        }

        report("autoGenerateHandle", autoGenerateHandle);
        report("defaultHandle", defaultHandle);
    }

    private static void report(String tag, List<Long> list) {
        long sum = 0;
        for (Long l : list) {
            sum += l;
        }

        long average = sum / list.size();

        System.out.println(tag + " (" + list.size() + " request):");
        System.out.println("\tsum: \t" + sum + " ns - " + TimeUnit.NANOSECONDS.toMillis(sum) + " ms");
        System.out.println("\taverage: \t" + average + " ns - " + TimeUnit.NANOSECONDS.toMillis(average) + " ms");
    }

    public static ApplicationContext run(String... args) {
        SpringApplication application = new SpringApplication(SpringDataMappingValidationApplication.class);

        application.addInitializers(new MappingValidationBeanInitializer());

        return application.run(args);
    }

}
