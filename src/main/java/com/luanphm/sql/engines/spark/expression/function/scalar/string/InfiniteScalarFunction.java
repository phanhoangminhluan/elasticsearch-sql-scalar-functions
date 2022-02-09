package com.luanphm.sql.engines.spark.expression.function.scalar.string;

import static org.elasticsearch.xpack.ql.expression.gen.script.ParamsBuilder.paramsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.function.scalar.ScalarFunction;
import org.elasticsearch.xpack.ql.expression.gen.script.ParamsBuilder;
import org.elasticsearch.xpack.ql.expression.gen.script.ScriptTemplate;
import org.elasticsearch.xpack.ql.tree.Source;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.01.02 15:19
 */
public abstract class InfiniteScalarFunction extends ScalarFunction {

    private final List<Expression> expressions;

    protected InfiniteScalarFunction(Source source, List<Expression> fields) {
        super(source, fields);
        this.expressions = fields;
    }

    public List<Expression> expressions() {
        return this.expressions;
    }

    @Override
    public ScriptTemplate asScript() {
        List<ScriptTemplate> templates = new ArrayList<>();
        for (Expression ex : children()) {
            templates.add(asScript(ex));
        }

        StringJoiner template = new StringJoiner(",", "{sql}." + "concat" +"([", "])");
        ParamsBuilder params = paramsBuilder();

        for (ScriptTemplate scriptTemplate : templates) {
            template.add(scriptTemplate.template());
            params.script(scriptTemplate.params());
        }

        return new ScriptTemplate(formatTemplate(template.toString()), params.build(), dataType());
    }

    @Override
    public abstract InfiniteScalarFunction replaceChildren(List<Expression> newChildren);
}
