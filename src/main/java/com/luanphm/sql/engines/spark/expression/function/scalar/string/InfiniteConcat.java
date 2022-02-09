package com.luanphm.sql.engines.spark.expression.function.scalar.string;

import java.util.List;
import java.util.stream.Collectors;

import com.luanphm.sql.engines.spark.expression.function.processor.InfiniteConcatFunctionProcessor;
import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.gen.script.ScriptTemplate;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.DataType;
import org.elasticsearch.xpack.ql.type.DataTypes;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.01.02 15:17
 */
public class InfiniteConcat extends InfiniteScalarFunction{
    public InfiniteConcat(Source source, List<Expression> fields) {
        super(source, fields);
    }

    @Override
    public ScriptTemplate asScript() {
        return null;
    }

    @Override
    public InfiniteConcat replaceChildren(List<Expression> newChildren) {
        return new InfiniteConcat(source(), newChildren);
    }

    @Override
    public Object fold() {
        List<Object> folded = this.expressions()
                .stream()
                .map(Expression::fold)
                .collect(Collectors.toList());
        return InfiniteConcatFunctionProcessor.process(folded);
    }

    @Override
    public boolean foldable() {
        return this.expressions().stream().allMatch(Expression::foldable);
    }

    @Override
    protected NodeInfo<? extends Expression> info() {
        return NodeInfo.create(this, InfiniteConcat::new, expressions());
    }


    @Override
    public DataType dataType() {
        return DataTypes.KEYWORD;
    }
}
