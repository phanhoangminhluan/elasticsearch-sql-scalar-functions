package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import java.time.ZoneId;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.gen.pipeline.Pipe;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.ThreeArgsDateTimePipe;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 11:04
 */
public class FormattedDateAddPipe extends ThreeArgsDateTimePipe {
    public FormattedDateAddPipe(Source source, Expression expression, Pipe first, Pipe second, Pipe third, ZoneId zoneId) {
        super(source, expression, first, second, third, zoneId);
    }

    @Override
    protected NodeInfo<? extends Pipe> info() {
        return NodeInfo.create(this, FormattedDateAddPipe::new, expression(), first(), second(), third(), zoneId());
    }

    @Override
    public ThreeArgsDateTimePipe replaceChildren(Pipe newFirst, Pipe newSecond, Pipe newThird) {
        return new FormattedDateAddPipe(source(), expression(), newFirst, newSecond, newThird, zoneId());
    }

    @Override
    protected Processor makeProcessor(Processor first, Processor second, Processor third, ZoneId zoneId) {
        return new FormattedDateAddProcessor(first, second, third, zoneId);
    }
}
