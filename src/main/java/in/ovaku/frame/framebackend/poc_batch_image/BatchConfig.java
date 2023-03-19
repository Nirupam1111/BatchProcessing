package in.ovaku.frame.framebackend.poc_batch_image;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MyCustomReader myCustomReader;
    @Autowired
    private MyCustomProcessor myCustomProcessor;
    @Autowired
    private MyCustomWriter myCustomWriter;

    @Bean
    public Step step() {
        return stepBuilderFactory.get("MyStep").<MultipartFile, MultipartFile>chunk(3)
                .reader(myCustomReader)
                .processor(myCustomProcessor)
                .writer(myCustomWriter)
                .build();
    }

    @Bean
    public Job runBatchJob() {
        return jobBuilderFactory.get("batch")
                .flow(step()).end().build();
    }

}
