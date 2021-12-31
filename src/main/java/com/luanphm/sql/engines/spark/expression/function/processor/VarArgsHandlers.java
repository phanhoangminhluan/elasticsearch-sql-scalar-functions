package com.luanphm.sql.engines.spark.expression.function.processor;

import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.ql.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.30 01:23
 */
public class VarArgsHandlers {

    public static String concat(Collection<String> values) {

        if (values == null || values.isEmpty()) {
            return null;
        }

        StringBuilder result = null;

        for (String value : values) {

            if (value == null) {
                continue;
            }

            if (result == null) {
                result = new StringBuilder(value);
            } else {
                result.append(value);
            }
        }

        return result == null ? null : result.toString();
    }

    public static String concatInput(List<Processor> processors, String input) {
        StringBuilder result = null;
        for (Processor processor : processors) {
            Object singleResult = processor.process(input);
            if (singleResult == null) {
                continue;
            }

            if (result == null) {
                result = new StringBuilder(singleResult.toString());
            } else {
                result.append(singleResult);
            }
        }
        return result == null ? null : result.toString();
    }

}
