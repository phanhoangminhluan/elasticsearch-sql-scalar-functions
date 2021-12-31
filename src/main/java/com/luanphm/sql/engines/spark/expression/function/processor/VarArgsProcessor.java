package com.luanphm.sql.engines.spark.expression.function.processor;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.sql.expression.predicate.conditional.ConditionalProcessor;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.12.30 01:20
 */
public class VarArgsProcessor implements Processor {


    public enum VarArgsOperation implements Function<Collection<String>, String> {

        CONCAT(VarArgsHandlers::concat, VarArgsHandlers::concatInput)

        ;

        private final Function<Collection<String>, String> process;
        private final BiFunction<List<Processor>, String, String> inputProcess;

        VarArgsOperation(Function<Collection<String>, String> process, BiFunction<List<Processor>, String, String> inputProcess) {
            this.process = process;
            this.inputProcess = inputProcess;
        }

        @Override
        public String apply(Collection<String> strings) {
            return process.apply(strings);
        }


        String applyOnInput(List<Processor> processors, String input) {
            return inputProcess.apply(processors, input);
        }

    }

    public static final String NAME = "nvar";


    private final List<Processor> processors;
    private final VarArgsOperation operation;

    public VarArgsProcessor(List<Processor> processors, VarArgsOperation operation) {
        this.processors = processors;
        this.operation = operation;
    }

    public VarArgsProcessor(StreamInput in) throws IOException {
        processors = in.readNamedWriteableList(Processor.class);
        operation = in.readEnum(VarArgsOperation.class);
    }

    @Override
    public Object process(Object input) {
        return operation.applyOnInput(processors, input.toString());
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeNamedWriteableList(processors);
        out.writeEnum(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VarArgsProcessor that = (VarArgsProcessor) o;
        return Objects.equals(processors, that.processors) &&
                operation == that.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(processors, operation);
    }
}
