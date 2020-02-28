package com.example.demo.mail;

import com.example.demo.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
@Component
public class CreateMailsReader implements ItemReader<String>, StepExecutionListener {

    private int linesToSkip = 0;
    private String resourcePath;
    private Long campaignId;

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Asserts.assertNotEmpty(resourcePath, "resourcePath is null. set resource path");

        URL url = CreateMailsWriter.class.getResource(resourcePath);
        if (url == null)
            throw new IllegalStateException("resourcePath does not exists. " + resourcePath);

        campaignId = stepExecution.getExecutionContext().getLong("campaignId");
        Asserts.assertNotNull(campaignId, "campaignId is null");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CreateMailsWriter.class.getResourceAsStream(resourcePath)))) {
            // skipping.
            for (int i = 0; i < linesToSkip; i++) {
                reader.readLine();
            }

            String line = reader.readLine();
            if (line != null)
                linesToSkip++;

            return line;

        } catch (IOException ioe) {
            log.error("dammit chloe!", ioe);
            throw new RuntimeException(ioe);
        }
    }
}
