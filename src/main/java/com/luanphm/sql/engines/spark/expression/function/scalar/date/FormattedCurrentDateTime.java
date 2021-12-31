package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import org.elasticsearch.xpack.ql.session.Configuration;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.sql.type.SqlDataTypes;
import org.elasticsearch.xpack.sql.util.DateUtils;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 06:29
 */
public class FormattedCurrentDateTime extends CustomCurrentFunction<String>{

    public FormattedCurrentDateTime(Source source, Configuration configuration) {
        super(
                source,
                configuration,
                DateUtils.convertZonedDateTimeToFormat(configuration.now(), configuration.now().getZone().getId(), DateUtils.ES_LOCAL_DATE_TIME),
                SqlDataTypes.DATE
        );
    }

    @Override
    protected NodeInfo<FormattedCurrentDateTime> info() {
        return NodeInfo.create(this, FormattedCurrentDateTime::new, configuration());
    }
}
