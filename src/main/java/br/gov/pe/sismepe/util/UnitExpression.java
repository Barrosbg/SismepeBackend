package br.gov.pe.sismepe.util;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.function.BasicFunctionExpression;

import java.io.Serializable;

public class UnitExpression extends BasicFunctionExpression<String> implements Serializable {
    public UnitExpression(CriteriaBuilderImpl criteriaBuilder, Class<String> javaType, String functionName) {
        super(criteriaBuilder, javaType, functionName);
    }

    public String render(RenderingContext renderingContext) {
        return getFunctionName();
    }
}
