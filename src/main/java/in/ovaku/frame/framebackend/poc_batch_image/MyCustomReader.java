package in.ovaku.frame.framebackend.poc_batch_image;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MyCustomReader implements ItemReader<MultipartFile> {
    private int index = 0;

    @Override
    public MultipartFile read() throws Exception {
        ImageController imageController = new ImageController();
        MultipartFile[] files = imageController.getMyFiles();
        if (index >= files.length)
            return null;
        return files[index++];
    }

}
