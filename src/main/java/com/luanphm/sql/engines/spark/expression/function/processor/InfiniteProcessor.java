package com.luanphm.sql.engines.spark.expression.function.processor;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.01.02 15:36
 */
public abstract class InfiniteProcessor implements Processor {

    private final List<Processor> processors;

    public InfiniteProcessor(List<Processor> processors) {
        this.processors = processors;
    }

    @Override
    public Object process(Object input) {
        List<Object> objs = new ArrayList<>();
        for (Processor processor : processors) {
            objs.add(processor.process(input));
        }
        return doProcess(objs);
    }

    public List<Processor> processors() {
        return processors;
    }

    protected abstract Object doProcess(List<Object> objs);

}
