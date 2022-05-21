package ru.hogwarts.school.service.Intetface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarServiceInterface {

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    public Avatar findAvatar(Long studentId);

    public List<Avatar> getAvatarsByPage(Integer pageSize, Integer pageNumber);
}
