package com.soulroomie.demo.tools.http;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
class HttpHelperTest {

    @Test
    void upload() throws IOException {
        HttpHelper httpHelper = new HttpHelper();
        String path = openFile();
        if (!path.equals("")) {
            File file = new File(path);
            byte[] fileByte = Files.readAllBytes(file.toPath());
            String imageName = UUID.randomUUID().toString();
            imageName = imageName + ".jpg";
            System.out.println(httpHelper.upload(fileByte));
        }
    }


    String openFile() {

        JFileChooser chooser = new JFileChooser("E:\\study\\Pictures");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = chooser.getSelectedFile();

            return file.getAbsolutePath();
        } else {

            return "";
        }
    }
}