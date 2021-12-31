package org.elasticsearch.xpack.ql.expression.function.scalar;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.gen.script.ScriptTemplate;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;

import java.util.Arrays;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.11.23 15:46
 */
public abstract class VarArgsScalarFunction extends ScalarFunction{
    protected VarArgsScalarFunction(Source source, List<Expression> fields) {
        super(source, fields);
    }

    @Override
    public ScriptTemplate asScript() {
        return null;
    }

    @Override
    public abstract VarArgsScalarFunction replaceChildren(List<Expression> newChildren);

    @Override
    protected NodeInfo<? extends Expression> info() {
        return null;
    }
}
