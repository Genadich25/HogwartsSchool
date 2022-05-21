package ru.hogwarts.school.service.Intetface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarServiceInterface {

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    public Avatar findAvatar(Long studentId);

}
