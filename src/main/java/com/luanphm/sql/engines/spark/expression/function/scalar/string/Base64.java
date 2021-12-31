package com.luanphm.sql.engines.spark.expression.function.scalar.string;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.function.scalar.UnaryScalarFunction;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.DataType;
import org.elasticsearch.xpack.ql.type.DataTypes;
import org.elasticsearch.xpack.sql.expression.function.scalar.string.StringProcessor;
import org.elasticsearch.xpack.sql.expression.function.scalar.string.UnaryStringFunction;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.29 19:56
 */
public class Base64 extends UnaryStringFunction {


    public Base64(Source source, Expression field) {
        super(source, field);
    }

    @Override
    public DataType dataType() {
        return DataTypes.KEYWORD;
    }

    @Override
    protected UnaryScalarFunction replaceChild(Expression newChild) {
        return new Md5(source(), newChild);
    }

    @Override
    protected NodeInfo<? extends Expression> info() {
        return NodeInfo.create(this, Md5::new, field());
    }

    @Override
    protected StringProcessor.StringOperation operation() {
        return StringProcessor.StringOperation.BASE64;
    }
}
