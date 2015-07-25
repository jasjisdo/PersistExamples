package de.dailab.schaufenster.jpafilter.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FilterQuery {
 String name()  default "";
 String jpql()  default "";
}