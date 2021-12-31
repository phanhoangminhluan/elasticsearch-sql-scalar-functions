package com.luanphm.sql.engines.spark.expression.function.scalar.string;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.Expressions;
import org.elasticsearch.xpack.ql.expression.Nullability;
import org.elasticsearch.xpack.ql.expression.TypeResolutions;
import org.elasticsearch.xpack.ql.expression.function.scalar.VarArgsScalarFunction;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.DataType;
import org.elasticsearch.xpack.ql.type.DataTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.xpack.ql.expression.TypeResolutions.isStringAndExact;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.11.23 15:45
 */
public class EnhancedConcat extends VarArgsScalarFunction {

    private final List<Expression> fields;


    public EnhancedConcat(Source source, List<Expression> fields) {
        super(source, fields);
        this.fields = fields;
    }

    @Override
    protected TypeResolution resolveType() {

//        for (int i = 0; i < fields.size(); i++) {
//            Expression field = fields.get(i);
//
//            TypeResolution typeResolution = TypeResolutions.isString(field, sourceText(), Expressions.ParamOrdinal.fromIndex(i));
//            if (typeResolution.unresolved()) {
//                return typeResolution;
//            }
//        }

        return TypeResolution.TYPE_RESOLVED;
    }



    @Override
    public EnhancedConcat replaceChildren(List<Expression> newChildren) {
        return new EnhancedConcat(source(), newChildren);

    }

    @Override
    public Object fold() {
        return null;
//        List<Expression> children = children();
//        return children.isEmpty() ? null : children.get(0).fold();
    }

    @Override
    public boolean foldable() {
        return false;
//        List<Expression> children = children();
//        return (children.isEmpty() || (children.get(0).foldable() && children.get(0).fold() != null));
    }

    @Override
    public Nullability nullable() {
        return Nullability.FALSE;
    }

    @Override
    public DataType dataType() {
        return DataTypes.KEYWORD;
    }
}
