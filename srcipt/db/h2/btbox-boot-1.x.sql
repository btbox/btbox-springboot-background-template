
-- ----------------------------
-- 1、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null                   comment '日志主键',
  title             varchar2(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar2(100)    default ''                 comment '方法名称',
  request_method    varchar2(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar2(50)     default ''                 comment '操作人员',
  dept_name         varchar2(50)     default ''                 comment '部门名称',
  oper_url          varchar2(255)    default ''                 comment '请求URL',
  oper_ip           varchar2(128)    default ''                 comment '主机地址',
  oper_location     varchar2(255)    default ''                 comment '操作地点',
  oper_param        varchar2(2000)   default ''                 comment '请求参数',
  json_result       varchar2(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar2(2000)   default ''                 comment '错误消息',
  oper_time         timestamp                                   comment '操作时间',
  primary key (oper_id),
  key idx_sys_oper_log_bt (business_type),
  key idx_sys_oper_log_s  (status),
  key idx_sys_oper_log_ot (oper_time)
) comment = '操作日志记录';


-- ----------------------------
-- 2、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null                   comment '字典主键',
  dict_name        varchar2(100)    default ''                 comment '字典名称',
  dict_type        varchar2(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar2(64)     default ''                 comment '创建者',
  create_time      timestamp                                   comment '创建时间',
  update_by        varchar2(64)     default ''                 comment '更新者',
  update_time      timestamp                                   comment '更新时间',
  remark           varchar2(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) comment = '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin',  current_timestamp(), '', null, '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin',  current_timestamp(), '', null, '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin',  current_timestamp(), '', null, '系统开关列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin',  current_timestamp(), '', null, '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin',  current_timestamp(), '', null, '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin',  current_timestamp(), '', null, '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin',  current_timestamp(), '', null, '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin',  current_timestamp(), '', null, '登录状态列表');


-- ----------------------------
-- 3、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null                   comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar2(100)    default ''                 comment '字典标签',
  dict_value       varchar2(100)    default ''                 comment '字典键值',
  dict_type        varchar2(100)    default ''                 comment '字典类型',
  css_class        varchar2(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar2(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar2(64)     default ''                 comment '创建者',
  create_time      timestamp                                   comment '创建时间',
  update_by        varchar2(64)     default ''                 comment '更新者',
  update_time      timestamp                                   comment '更新时间',
  remark           varchar2(500)    default null               comment '备注',
  primary key (dict_code)
) comment = '字典数据表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin',  current_timestamp(), '', null, '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin',  current_timestamp(), '', null, '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin',  current_timestamp(), '', null, '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin',  current_timestamp(), '', null, '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin',  current_timestamp(), '', null, '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '停用状态');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin',  current_timestamp(), '', null, '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin',  current_timestamp(), '', null, '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin',  current_timestamp(), '', null, '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin',  current_timestamp(), '', null, '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '关闭状态');
insert into sys_dict_data values(29, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin',  current_timestamp(), '', null, '其他操作');
insert into sys_dict_data values(18, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin',  current_timestamp(), '', null, '新增操作');
insert into sys_dict_data values(19, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin',  current_timestamp(), '', null, '修改操作');
insert into sys_dict_data values(20, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '删除操作');
insert into sys_dict_data values(21, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin',  current_timestamp(), '', null, '授权操作');
insert into sys_dict_data values(22, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin',  current_timestamp(), '', null, '导出操作');
insert into sys_dict_data values(23, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin',  current_timestamp(), '', null, '导入操作');
insert into sys_dict_data values(24, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '强退操作');
insert into sys_dict_data values(25, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin',  current_timestamp(), '', null, '生成操作');
insert into sys_dict_data values(26, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '清空操作');
insert into sys_dict_data values(27, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin',  current_timestamp(), '', null, '正常状态');
insert into sys_dict_data values(28, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin',  current_timestamp(), '', null, '停用状态');
-- ----------------------------

-- ----------------------------
-- 4、文件数据表
-- ----------------------------
drop table if exists `file_detail`;
create table `file_detail`
(
    `id`                varchar2(32)  not null COMMENT '文件id',
    `url`               varchar2(512) not null COMMENT '文件访问地址',
    `size`              bigint(20)   default null COMMENT '文件大小，单位字节',
    `filename`          varchar2(256) default null COMMENT '文件名称',
    `original_filename` varchar2(256) default null COMMENT '原始文件名',
    `base_path`         varchar2(256) default null COMMENT '基础存储路径',
    `path`              varchar2(256) default null COMMENT '存储路径',
    `ext`               varchar2(32)  default null COMMENT '文件扩展名',
    `content_type`      varchar2(128)  default null COMMENT 'MIME类型',
    `platform`          varchar2(32)  default null COMMENT '存储平台',
    `th_url`            varchar2(512) default null COMMENT '缩略图访问路径',
    `th_filename`       varchar2(256) default null COMMENT '缩略图名称',
    `th_size`           bigint(20)   default null COMMENT '缩略图大小，单位字节',
    `th_content_type`   varchar2(128)  default null COMMENT '缩略图MIME类型',
    `object_id`         varchar2(32)  default null COMMENT '文件所属对象id',
    `object_type`       varchar2(32)  default null COMMENT '文件所属对象类型，例如用户头像，评价图片',
    `attr`              clob COMMENT '附加属性',
    `file_acl`          varchar2(32)  default null COMMENT '文件ACL',
    `th_file_acl`       varchar2(32)  default null COMMENT '缩略图文件ACL',
    `create_time`       timestamp     default null COMMENT '创建时间',
    PRIMARY KEY (`id`)
) comment ='文件记录表';