package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import org.elasticsearch.xpack.ql.session.Configuration;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.sql.type.SqlDataTypes;
import org.elasticsearch.xpack.sql.util.DateUtils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.30 23:10
 */
public class FormattedCurrentDate extends CustomCurrentFunction<String> {

    public FormattedCurrentDate(Source source, Configuration configuration) {
        super(
                source,
                configuration,
                DateUtils.convertZonedDateTimeToFormat(configuration.now(), configuration.now().getZone().getId(), DateTimeFormatter.ISO_LOCAL_DATE),
                SqlDataTypes.DATE
        );
    }

    @Override
    protected NodeInfo<FormattedCurrentDate> info() {
        return NodeInfo.create(this, FormattedCurrentDate::new, configuration());
    }
}

