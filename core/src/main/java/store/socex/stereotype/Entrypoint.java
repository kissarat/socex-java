package store.socex.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Entrypoint {
    String value() default "";
}
