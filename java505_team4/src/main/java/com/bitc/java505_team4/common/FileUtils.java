package com.bitc.java505_team4.common;

import com.bitc.java505_team4.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.connector.Request;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.JstlUtils;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// @Bean과 @Component는 동일하게 스프링프레임워크에서 객체를 생성하여 사용하고 관리 라이브러리라는 의미의 어노테이션
//@Bean : 스프링프레임워크 및 각종 서드파티 회사에서 제공하는 미리 생성해 놓은 라이브러리
//@Component : 사용자가 직접 생성한 라이브러리

@Component
public class FileUtils {

    public List<UserDto> parseFileInfo(UserDto user, MultipartHttpServletRequest uploadFiles) throws Exception {

        if (ObjectUtils.isEmpty(uploadFiles)) {
            return null;
        }

        List<UserDto> fileList = new ArrayList<UserDto>();

//    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    ZonedDateTime current = ZonedDateTime.now();

//    String path = "C:/upload";
        String path = "/image";

//    URL resource = getClass().getClassLoader().getResource("");
//    System.out.println(resource);


        String path2 = "C:\\smart505\\team4\\java505_team4\\src\\main\\resources\\static\\image";

        File file = new File(path2);

        if (file.exists() == false) {
            file.mkdirs();
        }

        Iterator<String> iterator = uploadFiles.getFileNames();

        String newFileName;
        String originalFileExtension;
        String contentType;

        while (iterator.hasNext()) {
            List<MultipartFile> fileLists = uploadFiles.getFiles(iterator.next());

            for (MultipartFile multipartFile : fileLists) {
                if (multipartFile.isEmpty() == false) {
                    contentType = multipartFile.getContentType();

                    if (ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        if (contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        } else if (contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        } else if (contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        } else {
                            break;
                        }
                    }

                    newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

                    user.setMemberEmail(user.getMemberEmail());
                    user.setMemberProfileName(multipartFile.getOriginalFilename()); // 파일 원본 이름
                    user.setMemberProfilePath(path + "/" + newFileName);

//          위에서 생성한 List<BoardFileDto> 타입의 변수에 데이터 추가
                    fileList.add(user);

//          자바의 File 클래스 객체를 방금 생성한 파일이름 및 경로로 생성함
                    file = new File(path2 + "/" + newFileName);
//          서버의 지정된 위치에 실제 파일을 복사함
                    multipartFile.transferTo(file);
                }
            }
        }
//    파일 정보 목록을 반환
        return fileList;
    }
}










