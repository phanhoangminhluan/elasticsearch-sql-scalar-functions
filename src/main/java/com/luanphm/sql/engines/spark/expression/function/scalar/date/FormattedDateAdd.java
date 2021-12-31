package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import java.time.ZoneId;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.gen.pipeline.Pipe;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.DateAdd;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.DateAddPipe;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.ThreeArgsDateTimeFunction;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 11:00
 */
public class FormattedDateAdd extends DateAdd {

    public FormattedDateAdd(Source source, Expression first, Expression second, Expression third, ZoneId zoneId) {
        super(source, first, second, third, zoneId);
    }

    @Override
    protected ThreeArgsDateTimeFunction replaceChildren(Expression newFirst, Expression newSecond, Expression newThird) {
        return new FormattedDateAdd(source(), newFirst, newSecond, newThird, zoneId());
    }

    @Override
    protected NodeInfo<? extends Expression> info() {
        return NodeInfo.create(this, DateAdd::new, first(), second(), third(), zoneId());
    }

    @Override
    protected Pipe createPipe(Pipe unit, Pipe numberOfUnits, Pipe timestamp, ZoneId zoneId) {
        return new FormattedDateAddPipe(source(), this, unit, numberOfUnits, timestamp, zoneId);
    }

    @Override
    public Object fold() {
        return FormattedDateAddProcessor.process(first().fold(), second().fold(), third().fold(), zoneId());
    }
}
