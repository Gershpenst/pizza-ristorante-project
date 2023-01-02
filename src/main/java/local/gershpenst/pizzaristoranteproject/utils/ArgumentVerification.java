package local.gershpenst.pizzaristoranteproject.utils;

import java.util.function.Consumer;

public class ArgumentVerification {

    public static <T> void updateIfPresent(Consumer<T> consumer, T value) {
        if(value != null) {
            consumer.accept(value);
        }
    }

}
