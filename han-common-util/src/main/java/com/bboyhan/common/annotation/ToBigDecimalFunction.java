package com.bboyhan.common.annotation;

import java.math.BigDecimal;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 17:50
 */
@FunctionalInterface
public interface ToBigDecimalFunction<T> {
	BigDecimal applyAsBigDecimal(T value);
}
