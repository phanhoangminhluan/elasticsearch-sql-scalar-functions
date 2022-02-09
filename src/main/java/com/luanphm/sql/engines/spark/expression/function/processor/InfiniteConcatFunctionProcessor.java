package com.luanphm.sql.engines.spark.expression.function.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.ql.util.StringUtils;
import org.elasticsearch.xpack.sql.SqlIllegalArgumentException;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.01.02 15:47
 */
public class InfiniteConcatFunctionProcessor extends InfiniteProcessor{

    public static final String NAME = "scon2";

    public InfiniteConcatFunctionProcessor(List<Processor> processors) {
        super(processors);
    }

    @Override
    protected Object doProcess(List<Object> objs) {
        return process(objs);
    }


    /**
     * Used in Painless scripting
     */
    public static Object process(List<Object> objs) {

        List<Object> nonNullObjects = new ArrayList<>();
        for (Object obj : objs) {
            if (obj == null) {
                continue;
            }
            if ((obj instanceof String || obj instanceof Character) == false) {
                throw new SqlIllegalArgumentException("A string/char is required; received [{}]", obj);
            }

            nonNullObjects.add(obj);
        }

        StringBuilder result = new StringBuilder();

        if (nonNullObjects.isEmpty()) {
            return StringUtils.EMPTY;
        }

        for (Object nonNullObject : nonNullObjects) {
            result.append(nonNullObject.toString());
        }
        return result.toString();
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        InfiniteConcatFunctionProcessor other = (InfiniteConcatFunctionProcessor) obj;

        int curSize = this.processors().size();
        if (curSize != other.processors().size()) {
            return false;
        }

        for (int i = 0; i < curSize; i++) {
            if (!Objects.equals(this.processors().get(i), other.processors().get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(processors().toArray());
    }
}
