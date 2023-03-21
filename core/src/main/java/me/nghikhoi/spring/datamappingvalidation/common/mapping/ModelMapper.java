package me.nghikhoi.spring.datamappingvalidation.common.mapping;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

@RequiredArgsConstructor
public class ModelMapper<F, T> {

    public static <F, T> ModelMapper<F, T> newModelMapper(Class<F> fromClass, Class<T> toClass, Comparator<String> fieldNameMatcher) {
        Field[] fromFields = fromClass.getDeclaredFields();
        Field[] toFields = toClass.getDeclaredFields();
        Arrays.sort(toFields, Comparator.comparing(Field::getName));

        FieldPair[] pairs = new FieldPair[fromFields.length];
        Constructor<T> toConstructor = null;
        try {
            toConstructor = toClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Error on finding no parameters constructor", e);
        }
        try {
            for (int i = 0; i < fromFields.length; i++) {
                Field fromField = fromFields[i];
                Field toField = toFields[Arrays.binarySearch(toFields, fromField
                        , (a, b) -> fieldNameMatcher.compare(a.getName(), b.getName()))];

                if (toField == null) {
                    throw new IllegalArgumentException("No field found in " + toClass.getName() + " for " + fromField.getName());
                }

                fromField.setAccessible(true);
                toField.setAccessible(true);

                pairs[i] = new FieldPair(fromField, toField);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error on finding match fields", e);
        }

        return new ModelMapper<>(pairs, toConstructor);
    }

    private static class FieldPair {
        private final Field from;
        private final Field to;

        public FieldPair(Field from, Field to) {
            this.from = from;
            this.to = to;
        }

        public void transferValue(Object fromObject, Object toObject) throws IllegalAccessException {
            to.set(toObject, from.get(fromObject));
        }
    }

    private final FieldPair[] pairs;
    private final Constructor<T> toConstructor;

    @SneakyThrows
    public void map(F from, T to) {
        for (FieldPair pair : pairs) {
            pair.transferValue(from, to);
        }
    }

    @SneakyThrows
    public T map(F from) {
        T to = toConstructor.newInstance();
        map(from, to);
        return to;
    }

}
