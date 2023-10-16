package com.btbox.demo.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import cn.xuyanwu.spring.file.storage.ProgressListener;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/file")
public class FileDetailController {


    private final FileStorageService fileStorageService;// 注入实列

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) {
        return fileStorageService.of(file)
                .setPath("upload/") // 保存到相对路径下，为了方便管理，不需要可以不写
                .setObjectId("0")   // 关联对象id，为了方便管理，不需要可以不写
                .setObjectType("0") // 关联对象类型，为了方便管理，不需要可以不写
                .putAttr("role","admin") // 保存一些属性，可以在切面、保存上传记录、自定义存储平台等地方获取使用，不需要可以不写
                .upload();
    }
    
    /**
     * 上传文件，成功返回文件 url
     */
    @PostMapping("/upload2")
    public String upload2(MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file)
                .setPath("upload/") // 保存到相对路径下，为了方便管理，不需要可以不写
                .setObjectId("0")   // 关联对象id，为了方便管理，不需要可以不写
                .setObjectType("0") // 关联对象类型，为了方便管理，不需要可以不写
                .putAttr("role","admin") // 保存一些属性，可以在切面、保存上传记录、自定义存储平台等地方获取使用，不需要可以不写
                .upload();  // 将文件上传到对应地方
        return fileInfo == null ? "上传失败！" : fileInfo.getUrl();
    }

    /**
     * 上传图片，成功返回文件信息
     * 图片处理使用的是 https://github.com/coobird/thumbnailator
     */
    @PostMapping("/upload-image")
    public FileInfo uploadImage(MultipartFile file) {
        return fileStorageService.of(file)
                .image(img -> img.size(1000,1000))  // 将图片大小调整到 1000*1000
                .thumbnail(th -> th.size(200,200))  // 再生成一张 200*200 的缩略图
                .upload();
    }

    /**
     * 上传文件到指定存储平台，成功返回文件信息
     */
    @PostMapping("/upload-platform")
    public FileInfo uploadPlatform(MultipartFile file) {
        return fileStorageService.of(file)
                .setPlatform("aliyun-oss-1")    // 使用指定的存储平台
                .upload();
    }

    @PostMapping("/upload-listener")
    public FileInfo uploadListener(MultipartFile file) {
        return fileStorageService.of(file).setProgressMonitor(new ProgressListener() {
            @Override
            public void start() {
                System.out.println("上传开始");
            }

            @Override
            public void progress(long progressSize,long allSize) {
                System.out.println("已上传 " + progressSize + " 总大小" + allSize);
            }

            @Override
            public void finish() {
                System.out.println("上传结束");
            }
        }).upload();
    }

    @PostMapping("/download")
    public void uploadListener(String url) {
        FileInfo fileInfo = fileStorageService.getFileInfoByUrl(url);
        fileStorageService.download(fileInfo).setProgressMonitor((progressSize,allSize) ->
                System.out.println("已下载 " + progressSize + " 总大小" + allSize)
        ).file("D:\\Temp\\a.jpg");
    }
}
