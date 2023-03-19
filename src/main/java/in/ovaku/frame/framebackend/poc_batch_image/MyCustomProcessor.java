package in.ovaku.frame.framebackend.poc_batch_image;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MyCustomProcessor implements ItemProcessor<MultipartFile, MultipartFile> {
    @Override
    public MultipartFile process(MultipartFile file) throws Exception {

        return file;
    }
}
