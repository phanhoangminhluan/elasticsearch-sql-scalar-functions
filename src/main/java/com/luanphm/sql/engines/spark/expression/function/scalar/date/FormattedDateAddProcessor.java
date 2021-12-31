package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.sql.SqlIllegalArgumentException;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.DateAdd;
import org.elasticsearch.xpack.sql.util.DateUtils;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 10:14
 */
public class FormattedDateAddProcessor extends FormattedThreeArgsDateTimeProcessor{
    public FormattedDateAddProcessor(Processor first, Processor second, Processor third, ZoneId zoneId) {
        super(first, second, third, zoneId);
    }

    public static final String NAME = "dtadd";

    protected FormattedDateAddProcessor(StreamInput in) throws IOException {
        super(in);
    }

    @Override
    public String doProcess(Object unit, Object numberOfUnits, Object timestamp, ZoneId zoneId) {
        return process(unit, numberOfUnits, timestamp, zoneId);
    }

    public static String process(Object unit, Object numberOfUnits, Object timestamp, ZoneId zoneId) {
        if (unit == null || numberOfUnits == null || timestamp == null) {
            return null;
        }
        if (unit instanceof String == false) {
            throw new SqlIllegalArgumentException("A string is required; received [{}]", unit);
        }
        DateAdd.Part datePartField = DateAdd.Part.resolve((String) unit);
        if (datePartField == null) {
            List<String> similar = DateAdd.Part.findSimilar((String) unit);
            if (similar.isEmpty()) {
                throw new SqlIllegalArgumentException("A value of {} or their aliases is required; received [{}]",
                        DateAdd.Part.values(), unit);
            } else {
                throw new SqlIllegalArgumentException("Received value [{}] is not valid date part to add; " +
                        "did you mean {}?", unit, similar);
            }
        }

        if (numberOfUnits instanceof Number == false) {
            throw new SqlIllegalArgumentException("A number is required; received [{}]", numberOfUnits);
        }

        if (timestamp instanceof ZonedDateTime == false) {
            throw new SqlIllegalArgumentException("A date/datetime is required; received [{}]", timestamp);
        }

        ZonedDateTime zonedDateTime = datePartField.add(((ZonedDateTime) timestamp).withZoneSameInstant(zoneId), ((Number) numberOfUnits).intValue());
        return DateUtils.convertZonedDateTimeToFormat(zonedDateTime, zoneId.getId(), DateUtils.ES_LOCAL_DATE_TIME);
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }
}
