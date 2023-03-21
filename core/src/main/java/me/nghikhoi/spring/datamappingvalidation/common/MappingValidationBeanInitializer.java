package me.nghikhoi.spring.datamappingvalidation.common;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import me.nghikhoi.spring.datamappingvalidation.common.mapping.ModelMapper;
import me.nghikhoi.spring.datamappingvalidation.common.utils.ValidateUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class MappingValidationBeanInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        context.addBeanFactoryPostProcessor(factory -> {
            findAllClassesUsingClassLoader("me.nghikhoi.spring.datamappingvalidation.domain.args")
                    .forEach(clazz -> {
                        if (!clazz.isAnnotationPresent(ArgumentMapping.class)) {
                            return;
                        }

                        ArgumentMapping annotation = clazz.getAnnotation(ArgumentMapping.class);
                        Class fromClass = annotation.fromClass();

                        factory.registerSingleton(lowerFirstChar(fromClass.getSimpleName()) + "Validation", new ArgumentMappingValidation() {

                            private final ModelMapper modelMapper = ModelMapper.newModelMapper(fromClass, clazz, Comparator.naturalOrder());

                            @Override
                            public Either<Throwable, Object> validateAndMap(Object from) {
                                try {
                                    Object to = modelMapper.map(from);
                                    ValidateUtils.javaxValidate(to);
                                    return Either.right(to);
                                } catch (Exception e) {
                                    return Either.left(e);
                                }
                            }

                        });

                        log.info("Registered bean: " + lowerFirstChar(fromClass.getSimpleName()) + "Validation");
                    });
        });
    }

    private static String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }

    public Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

}
