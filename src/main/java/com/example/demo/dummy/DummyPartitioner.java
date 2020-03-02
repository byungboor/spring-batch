package com.example.demo.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@StepScope
@Component
public class DummyPartitioner implements Partitioner {

    private List<String> lines = List.of(
            "1,2,3,4,5,6,7,8,9,10",
            "a,b,c,d,e,f,g,h,i,j",
            "A,B,C,D,E,F,G,H,I,J"
    );

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>(gridSize);

        for (int i = 0; i < lines.size(); i++) {

            ExecutionContext executionContext = new ExecutionContext();
            executionContext.putString("Tokens", lines.get(i));
            map.put("key-" + i, executionContext);
        }

        log.info("partition result : {}, {}", map.size(), gridSize);

        return map;
    }
}
