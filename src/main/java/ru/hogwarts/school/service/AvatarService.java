package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.Intetface.AvatarServiceInterface;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService implements AvatarServiceInterface {
    @Value("${avatars.dir.path}")
    private String avatarsDir;

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    private final AvatarRepository repository;
    private final StudentService studentService;

    public AvatarService(AvatarRepository repository, StudentService service) {
        this.repository = repository;
        this.studentService = service;
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Optional<Student> oStudent = studentService.findStudent(studentId);
        Student student;
        if(oStudent.isPresent()){
            student = oStudent.get();
            logger.info("Student with {} found", studentId);
        } else {
            logger.error("There is not student with id {}", studentId);
            throw new NullPointerException();
        }

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImage(filePath));

        repository.save(avatar);
        logger.info("Avatar save in repository");
    }

    private byte[] generateImage(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            logger.debug("Preview is generated");
            return baos.toByteArray();
        }
    }

    public Avatar findAvatar(Long studentId){
        logger.debug("Find avatar with {}", studentId);
        return repository.findByStudentId(studentId).orElse(new Avatar());
    }

    public List<Avatar> getAvatarsByPage(Integer pageSize, Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        logger.debug("Pages created");
        return repository.findAll(pageRequest).getContent();
    }

    private String getExtension(String fileName){
        return fileName.substring((fileName.lastIndexOf(".") + 1));
    }
}
