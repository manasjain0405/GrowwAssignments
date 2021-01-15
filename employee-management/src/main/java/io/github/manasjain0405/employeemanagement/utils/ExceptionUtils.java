package io.github.manasjain0405.employeemanagement.utils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The type Exception utils.
 */
public final class ExceptionUtils {

  /**
   * Instantiates a new Exception utils.
   */
  private ExceptionUtils() {
    //empty constructor
  }

  /**
   * Bind supplier.
   *
   * @param <T>      the type parameter
   * @param <R>      the type parameter
   * @param function the function
   * @param value    the value
   * @return the supplier
   */
  public static <T, R> Supplier<R> bind(final Function<T,R> function, final T value) {
    return () -> function.apply(value);
  }
}
