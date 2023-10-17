package com.btbox.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
    * 文件记录表
    */
@Schema(description="文件记录表")
@Data
@TableName(value = "file_detail")
public class FileDetail {
    /**
     * 文件id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description="文件id")
    private String id;

    /**
     * 文件访问地址
     */
    @TableField(value = "url")
    @Schema(description="文件访问地址")
    private String url;

    /**
     * 文件大小，单位字节
     */
    @TableField(value = "`size`")
    @Schema(description="文件大小，单位字节")
    private Long size;

    /**
     * 文件名称
     */
    @TableField(value = "filename")
    @Schema(description="文件名称")
    private String filename;

    /**
     * 原始文件名
     */
    @TableField(value = "original_filename")
    @Schema(description="原始文件名")
    private String originalFilename;

    /**
     * 基础存储路径
     */
    @TableField(value = "base_path")
    @Schema(description="基础存储路径")
    private String basePath;

    /**
     * 存储路径
     */
    @TableField(value = "`path`")
    @Schema(description="存储路径")
    private String path;

    /**
     * 文件扩展名
     */
    @TableField(value = "ext")
    @Schema(description="文件扩展名")
    private String ext;

    /**
     * MIME类型
     */
    @TableField(value = "content_type")
    @Schema(description="MIME类型")
    private String contentType;

    /**
     * 存储平台
     */
    @TableField(value = "platform")
    @Schema(description="存储平台")
    private String platform;

    /**
     * 缩略图访问路径
     */
    @TableField(value = "th_url")
    @Schema(description="缩略图访问路径")
    private String thUrl;

    /**
     * 缩略图名称
     */
    @TableField(value = "th_filename")
    @Schema(description="缩略图名称")
    private String thFilename;

    /**
     * 缩略图大小，单位字节
     */
    @TableField(value = "th_size")
    @Schema(description="缩略图大小，单位字节")
    private Long thSize;

    /**
     * 缩略图MIME类型
     */
    @TableField(value = "th_content_type")
    @Schema(description="缩略图MIME类型")
    private String thContentType;

    /**
     * 文件所属对象id
     */
    @TableField(value = "object_id")
    @Schema(description="文件所属对象id")
    private String objectId;

    /**
     * 文件所属对象类型，例如用户头像，评价图片
     */
    @TableField(value = "object_type")
    @Schema(description="文件所属对象类型，例如用户头像，评价图片")
    private String objectType;

    /**
     * 附加属性
     */
    @TableField(value = "attr")
    @Schema(description="附加属性")
    private String attr;

    /**
     * 文件ACL
     */
    @TableField(value = "file_acl")
    @Schema(description="文件ACL")
    private String fileAcl;

    /**
     * 缩略图文件ACL
     */
    @TableField(value = "th_file_acl")
    @Schema(description="缩略图文件ACL")
    private String thFileAcl;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description="创建时间")
    private Date createTime;
}