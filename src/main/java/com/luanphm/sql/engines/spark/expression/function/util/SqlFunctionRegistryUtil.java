package com.luanphm.sql.engines.spark.expression.function.util;

import org.elasticsearch.xpack.ql.expression.function.FunctionDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.29 00:16
 */
public class SqlFunctionRegistryUtil {

    public static FunctionDefinition[][] merge(FunctionDefinition[][] originalFunctions, FunctionDefinition[][] sparkSqlFunctions) {

        List<FunctionDefinition[]> temp = new ArrayList<>();

        temp.addAll(Arrays.stream(originalFunctions).collect(Collectors.toList()));
        temp.addAll(Arrays.stream(sparkSqlFunctions).collect(Collectors.toList()));


        FunctionDefinition[][] result = new FunctionDefinition[temp.size()][];

        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }

        return result;
    }

}
