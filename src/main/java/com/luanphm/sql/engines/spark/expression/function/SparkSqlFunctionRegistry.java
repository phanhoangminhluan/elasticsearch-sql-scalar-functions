package com.luanphm.sql.engines.spark.expression.function;

import com.luanphm.sql.engines.spark.expression.function.scalar.date.FormattedCurrentDate;
import com.luanphm.sql.engines.spark.expression.function.scalar.date.FormattedCurrentDateTime;
import com.luanphm.sql.engines.spark.expression.function.scalar.date.FormattedDateAdd;
import com.luanphm.sql.engines.spark.expression.function.scalar.string.*;
import com.luanphm.sql.engines.spark.expression.function.util.SqlFunctionRegistryUtil;
import org.elasticsearch.xpack.ql.expression.function.FunctionDefinition;
import org.elasticsearch.xpack.sql.expression.function.SqlFunctionRegistry;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.CurrentDate;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.CurrentDateTime;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.DateAdd;
import org.elasticsearch.xpack.sql.expression.function.scalar.datetime.IsoWeekOfYear;
import org.elasticsearch.xpack.sql.expression.predicate.conditional.Coalesce;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.28 23:33
 */
public class SparkSqlFunctionRegistry extends SqlFunctionRegistry {

    @Override
    protected FunctionDefinition[][] functions() {
        return SqlFunctionRegistryUtil.merge(
                super.functions(),
                new FunctionDefinition[][] {
                        // String
                        new FunctionDefinition[] {
                                def(Md5.class, Md5::new, "CDP_MD5"),
                                def(Base64.class, Base64::new, "BASE64"),
                                def(Unbase64.class, Unbase64::new, "UNBASE64"),
                                def(Crc32.class, Crc32::new, "CDP_CRC32"),
                                def(Sha1.class, Sha1::new, "SHA1"),
                                def(Sha256.class, Sha256::new, "CDP_SHA256"),

                                def(EnhancedConcat.class, EnhancedConcat::new, "ENHANCED_CONCAT"),
                        },

                        // Date
                        new FunctionDefinition[] {
//                                def(FormattedCurrentDate.class, FormattedCurrentDate::new, "CURRENT_DATE"),
//                                def(FormattedCurrentDateTime.class, FormattedCurrentDateTime::new, "CURRENT_TIMESTAMP"),
                                def(IsoWeekOfYear.class, IsoWeekOfYear::new, "WEEK_OF_YEAR", "ISO_WEEK_OF_YEAR", "ISOWEEKOFYEAR", "ISOWEEK", "IWOY", "IW"),
//                                def(FormattedDateAdd.class, FormattedDateAdd::new, "DATEADD", "DATE_ADD")

                        }
                }
        );
    }
}


