package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.ThreeArgsDateTimeProcessor;

import java.io.IOException;
import java.time.ZoneId;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 10:08
 */
public abstract class FormattedThreeArgsDateTimeProcessor extends ThreeArgsDateTimeProcessor {
    public FormattedThreeArgsDateTimeProcessor(Processor first, Processor second, Processor third, ZoneId zoneId) {
        super(first, second, third, zoneId);
    }

    protected FormattedThreeArgsDateTimeProcessor(StreamInput in) throws IOException {
        super(in);
    }

    @Override
    public String process(Object input) {
        Object o1 = first().process(input);
        if (o1 == null) {
            return null;
        }
        Object o2 = second().process(input);
        if (o2 == null) {
            return null;
        }
        Object o3 = third().process(input);
        if (o3 == null) {
            return null;
        }

        return doProcess(o1, o2, o3, zoneId());
    }

    @Override
    public abstract String doProcess(Object o1, Object o2, Object o3, ZoneId zoneId);
}
