package in.ovaku.frame.framebackend.poc_batch_image;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MyCustomWriter implements ItemWriter<MultipartFile> {
    @Autowired
    BatchImageRepository batchImageRepository;

    @Override
    public void write(List<? extends MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            BatchImage img = new BatchImage();
            try {
                Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test");
                if (!Files.isDirectory(path))
                    Files.createDirectory(path);
                path = Paths.get(path + "\\" + file.getOriginalFilename());
                file.transferTo(path);

                img.setProfileImg(path.toString());
                img.setName(file.getOriginalFilename());
                img.setSize(String.valueOf(file.getSize()));

                batchImageRepository.save(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("3 photo uploaded");
    }

}
