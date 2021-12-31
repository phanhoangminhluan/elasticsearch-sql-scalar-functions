//package com.luanphm.sql.engines.spark.expression.function.scalar.string;
//
//import org.elasticsearch.xpack.ql.expression.Expression;
//import org.elasticsearch.xpack.ql.tree.NodeInfo;
//import org.elasticsearch.xpack.ql.tree.Source;
//import org.elasticsearch.xpack.sql.expression.predicate.conditional.ArbitraryConditionalFunction;
//
//import java.util.List;
//
//import static org.elasticsearch.xpack.sql.expression.predicate.conditional.ConditionalProcessor.ConditionalOperation.COALESCE;
//
///**
// * @author Minh-Luan H. Phan
// * Created on: 2021.12.30 01:38
// */
//public class Concat2 extends ArbitraryConditionalFunction {
//
//    public Concat2(Source source, List<Expression> fields) {
//        super(source, fields, COALESCE);
//    }
//
//
//    @Override
//    public Expression replaceChildren(List<Expression> newChildren) {
//        return null;
//    }
//
//    @Override
//    protected NodeInfo<? extends Expression> info() {
//        return null;
//    }
//}
