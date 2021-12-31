package com.luanphm.sql.engines.spark.expression.function.scalar.date;

import java.util.Objects;

import org.elasticsearch.xpack.ql.session.Configuration;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.DataType;
import org.elasticsearch.xpack.sql.expression.function.scalar.SqlConfigurationFunction;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.31 05:54
 */
public abstract class CustomCurrentFunction<T> extends SqlConfigurationFunction {
    private final T current;

    CustomCurrentFunction(Source source, Configuration configuration, T current, DataType dataType) {
        super(source, configuration, dataType);
        this.current = current;
    }

    @Override
    public Object fold() {
        return current;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CustomCurrentFunction other = (CustomCurrentFunction) obj;
        return Objects.equals(current, other.current);
    }
}
