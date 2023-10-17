-- ----------------------------
-- 1、操作日志记录
-- ----------------------------
create table sys_oper_log
(
    oper_id        number(20) not null,
    title          varchar2(50) default '',
    business_type  number(2) default 0,
    method         varchar2(100) default '',
    request_method varchar2(10) default '',
    operator_type  number(1) default 0,
    oper_name      varchar2(50) default '',
    dept_name      varchar2(50) default '',
    oper_url       varchar2(255) default '',
    oper_ip        varchar2(128) default '',
    oper_location  varchar2(255) default '',
    oper_param     varchar2(2100) default '',
    json_result    varchar2(2100) default '',
    status         number(1) default 0,
    error_msg      varchar2(2100) default '',
    oper_time      date
);

alter table sys_oper_log
    add constraint pk_sys_oper_log primary key (oper_id);
create index idx_sys_oper_log_bt on sys_oper_log (business_type);
create index idx_sys_oper_log_s on sys_oper_log (status);
create index idx_sys_oper_log_ot on sys_oper_log (oper_time);

comment
on table  sys_oper_log                is '操作日志记录';
comment
on column sys_oper_log.oper_id        is '日志主键';
comment
on column sys_oper_log.title          is '模块标题';
comment
on column sys_oper_log.business_type  is '业务类型（0其它 1新增 2修改 3删除）';
comment
on column sys_oper_log.method         is '方法名称';
comment
on column sys_oper_log.request_method is '请求方式';
comment
on column sys_oper_log.operator_type  is '操作类别（0其它 1后台用户 2手机端用户）';
comment
on column sys_oper_log.oper_name      is '操作人员';
comment
on column sys_oper_log.dept_name      is '部门名称';
comment
on column sys_oper_log.oper_url       is '请求URL';
comment
on column sys_oper_log.oper_ip        is '主机地址';
comment
on column sys_oper_log.oper_location  is '操作地点';
comment
on column sys_oper_log.oper_param     is '请求参数';
comment
on column sys_oper_log.json_result    is '返回参数';
comment
on column sys_oper_log.status         is '操作状态（0正常 1异常）';
comment
on column sys_oper_log.error_msg      is '错误消息';
comment
on column sys_oper_log.oper_time      is '操作时间';


-- ----------------------------
-- 2、字典类型表
-- ----------------------------
create table sys_dict_type
(
    dict_id     number(20) not null,
    dict_name   varchar2(100) default '',
    dict_type   varchar2(100) default '',
    status      char(1) default '0',
    create_by   varchar2(64) default '',
    create_time date,
    update_by   varchar2(64) default '',
    update_time date,
    remark      varchar2(500) default null
);

alter table sys_dict_type
    add constraint pk_sys_dict_type primary key (dict_id);
create unique index sys_dict_type_index1 on sys_dict_type (dict_type);

comment
on table  sys_dict_type               is '字典类型表';
comment
on column sys_dict_type.dict_id       is '字典主键';
comment
on column sys_dict_type.dict_name     is '字典名称';
comment
on column sys_dict_type.dict_type     is '字典类型';
comment
on column sys_dict_type.status        is '状态（0正常 1停用）';
comment
on column sys_dict_type.create_by     is '创建者';
comment
on column sys_dict_type.create_time   is '创建时间';
comment
on column sys_dict_type.update_by     is '更新者';
comment
on column sys_dict_type.update_time   is '更新时间';
comment
on column sys_dict_type.remark        is '备注';

insert into sys_dict_type
values (1, '用户性别', 'sys_user_sex', '0', 'admin', sysdate, '', null, '用户性别列表');
insert into sys_dict_type
values (2, '菜单状态', 'sys_show_hide', '0', 'admin', sysdate, '', null, '菜单状态列表');
insert into sys_dict_type
values (3, '系统开关', 'sys_normal_disable', '0', 'admin', sysdate, '', null, '系统开关列表');
insert into sys_dict_type
values (6, '系统是否', 'sys_yes_no', '0', 'admin', sysdate, '', null, '系统是否列表');
insert into sys_dict_type
values (7, '通知类型', 'sys_notice_type', '0', 'admin', sysdate, '', null, '通知类型列表');
insert into sys_dict_type
values (8, '通知状态', 'sys_notice_status', '0', 'admin', sysdate, '', null, '通知状态列表');
insert into sys_dict_type
values (9, '操作类型', 'sys_oper_type', '0', 'admin', sysdate, '', null, '操作类型列表');
insert into sys_dict_type
values (10, '系统状态', 'sys_common_status', '0', 'admin', sysdate, '', null, '登录状态列表');


-- ----------------------------
-- 3、字典数据表
-- ----------------------------
create table sys_dict_data
(
    dict_code   number(20) not null,
    dict_sort   number(4) default 0,
    dict_label  varchar2(100) default '',
    dict_value  varchar2(100) default '',
    dict_type   varchar2(100) default '',
    css_class   varchar2(100) default null,
    list_class  varchar2(100) default null,
    is_default  char(1) default 'N',
    status      char(1) default '0',
    create_by   varchar2(64) default '',
    create_time date,
    update_by   varchar2(64) default '',
    update_time date,
    remark      varchar2(500) default null
);

alter table sys_dict_data
    add constraint pk_sys_dict_data primary key (dict_code);

comment
on table  sys_dict_data               is '字典数据表';
comment
on column sys_dict_data.dict_code     is '字典主键';
comment
on column sys_dict_data.dict_sort     is '字典排序';
comment
on column sys_dict_data.dict_label    is '字典标签';
comment
on column sys_dict_data.dict_value    is '字典键值';
comment
on column sys_dict_data.dict_type     is '字典类型';
comment
on column sys_dict_data.css_class     is '样式属性（其他样式扩展）';
comment
on column sys_dict_data.list_class    is '表格回显样式';
comment
on column sys_dict_data.is_default    is '是否默认（Y是 N否）';
comment
on column sys_dict_data.status        is '状态（0正常 1停用）';
comment
on column sys_dict_data.create_by     is '创建者';
comment
on column sys_dict_data.create_time   is '创建时间';
comment
on column sys_dict_data.update_by     is '更新者';
comment
on column sys_dict_data.update_time   is '更新时间';
comment
on column sys_dict_data.remark        is '备注';

insert into sys_dict_data
values (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', sysdate, '', null, '性别男');
insert into sys_dict_data
values (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate, '', null, '性别女');
insert into sys_dict_data
values (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate, '', null, '性别未知');
insert into sys_dict_data
values (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', sysdate, '', null, '显示菜单');
insert into sys_dict_data
values (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '隐藏菜单');
insert into sys_dict_data
values (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data
values (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '停用状态');
insert into sys_dict_data
values (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', sysdate, '', null, '系统默认是');
insert into sys_dict_data
values (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '系统默认否');
insert into sys_dict_data
values (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', sysdate, '', null, '通知');
insert into sys_dict_data
values (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', sysdate, '', null, '公告');
insert into sys_dict_data
values (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data
values (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '关闭状态');
insert into sys_dict_data
values (29, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate, '', null, '其他操作');
insert into sys_dict_data
values (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate, '', null, '新增操作');
insert into sys_dict_data
values (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate, '', null, '修改操作');
insert into sys_dict_data
values (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '删除操作');
insert into sys_dict_data
values (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', sysdate, '', null, '授权操作');
insert into sys_dict_data
values (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate, '', null, '导出操作');
insert into sys_dict_data
values (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate, '', null, '导入操作');
insert into sys_dict_data
values (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '强退操作');
insert into sys_dict_data
values (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate, '', null, '生成操作');
insert into sys_dict_data
values (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '清空操作');
insert into sys_dict_data
values (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data
values (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', sysdate, '', null, '停用状态');


-- ----------------------------
-- 4、文件数据表
-- ----------------------------
create table file_detail
(
    "id" varchar2(32) not null,
    "url" varchar2(512) not null,
    "size" number(20) default null,
    "filename" varchar2(256) default null,
    "original_filename" varchar2(256) default null,
    "base_path" varchar2(256) default null,
    "path" varchar2(256) default null,
    "ext" varchar2(32) default null,
    "content_type" varchar2(128) default null,
    "platform" varchar2(32) default null,
    "th_url" varchar2(512) default null,
    "th_filename" varchar2(256) default null,
    "th_size" number(20) default null,
    "th_content_type" varchar2(128) default null,
    "object_id" varchar2(32) default null,
    "object_type" varchar2(32) default null,
    "attr" clob,
    "file_acl" varchar2(32) default null,
    "th_file_acl" varchar2(32) default null,
    "create_time" date default null
);

alter table file_detail
    add constraint pk_file_detail primary key ("id");

comment
on table file_detail is '文件数据表';
comment
on column file_detail."id" is '文件id';
comment
on column file_detail."url" is '文件访问地址';
comment
on column file_detail."size" is '文件大小，单位字节';
comment
on column file_detail."filename" is '文件名称';
comment
on column file_detail."original_filename" is '原始文件名';
comment
on column file_detail."base_path" is '基础存储路径';
comment
on column file_detail."path" is '存储路径';
comment
on column file_detail."ext" is '文件扩展名';
comment
on column file_detail."content_type" is 'MIME类型';
comment
on column file_detail."platform" is '存储平台';
comment
on column file_detail."th_url" is '缩略图访问路径';
comment
on column file_detail."th_filename" is '缩略图名称';
comment
on column file_detail."th_size" is '缩略图大小，单位字节';
comment
on column file_detail."th_content_type" is '缩略图MIME类型';
comment
on column file_detail."object_id" is '文件所属对象id';
comment
on column file_detail."object_type" is '文件所属对象类型，例如用户头像，评价图片';
comment
on column file_detail."attr" is '附加属性';
comment
on column file_detail."file_acl" is '文件ACL';
comment
on column file_detail."th_file_acl" is '缩略图文件ACL';
comment
on column file_detail."create_time" is '创建时间';


-- ----------------------------
-- 钩子 ，用于session连接之后 自动设置默认的date类型格式化 简化时间查询
-- 如需设置其它配置 可在此钩子内任意增加处理语句
-- 例如： SELECT * FROM sys_user WHERE create_time BETWEEN '2022-03-01 00:00:00' AND '2022-04-01 00:00:00'
-- ----------------------------
create
or replace trigger login_trg
after logon on database
begin
execute immediate 'alter
session set nls_date_format=''YYYY-MM-DD HH24:MI:SS''';
end;