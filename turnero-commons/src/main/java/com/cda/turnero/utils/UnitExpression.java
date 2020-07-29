package com.cda.turnero.utils;

import java.io.Serializable;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.function.BasicFunctionExpression;

public class UnitExpression extends BasicFunctionExpression<String> implements Serializable {

  public UnitExpression(CriteriaBuilderImpl criteriaBuilder, Class<String> javaType,
      String functionName) {
    super(criteriaBuilder, javaType, functionName);
  }

  @Override
  public String render(RenderingContext renderingContext) {
    return getFunctionName();
  }
}