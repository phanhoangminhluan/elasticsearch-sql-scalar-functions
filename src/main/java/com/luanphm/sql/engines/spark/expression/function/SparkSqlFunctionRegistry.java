package com.luanphm.sql.engines.spark.expression.function;

import com.luanphm.sql.engines.spark.expression.function.scalar.string.Md5;
import com.luanphm.sql.engines.spark.expression.function.util.SqlFunctionRegistryUtil;
import org.elasticsearch.xpack.ql.expression.function.FunctionDefinition;
import org.elasticsearch.xpack.sql.expression.function.SqlFunctionRegistry;

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
                                def(Md5.class, Md5::new, "MD5")
                        }
                }
        );
    }
}


