
/** DROP DATABASE **/
DROP DATABASE IF EXISTS `medical_appointment`;

/** CREATE DATABASE **/
CREATE DATABASE `medical_appointment` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_member` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `name`                    CHAR(20)                    NOT NULL                             COMMENT '姓名',
    `nickName`                VARCHAR(255)                                                     COMMENT '昵称',
    `mobile`                  VARCHAR(50)                 NOT NULL                             COMMENT '手机号',
    `avatar_url`              VARCHAR(255)                                                     COMMENT '用户头像',
    `member_id`               VARCHAR(255)                NOT NULL                             COMMENT '会员id',
    `gender`                  CHAR(10)                                                         COMMENT '性别 male | female | secrecy',
    `birthday`                DATE                                                             COMMENT '生日',
    `open_id`                 CHAR(255)                                                        COMMENT '微信OPEN ID',
    `province`                CHAR(50)                                                         COMMENT '省份',
    `city`                    CHAR(50)                                                         COMMENT '城市',
    `country`                 CHAR(50)                                                         COMMENT '区县',
    `is_active`               TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '状态 1:启用，0:禁用',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人',
    `deleted_at`              DATETIME                                                         COMMENT '删除时间',
    `deleted_by`              VARCHAR(50)                                                      COMMENT '删除人',
    `is_delete`               TINYINT(0)                           DEFAULT 1                   COMMENT '标记删除 1:未删除，0:已删除'
) COMMENT = '会员' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_account` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `name`                    CHAR(20)                    NOT NULL                             COMMENT '姓名',
    `account`                 VARCHAR(255)                NOT NULL                             COMMENT '账号',
    `parent_id`               BIGINT                                                           COMMENT '上级id',
    `hospital_id`             BIGINT                                                           COMMENT '医院id',
    `mobile`                  VARCHAR(50)                 NOT NULL                             COMMENT '手机号',
    `email`                   VARCHAR(255)                NOT NULL                             COMMENT '邮箱 多个邮箱用 ";" 隔开',
    `role_code`               VARCHAR(50)                 NOT NULL                             COMMENT '角色ID',
    `is_active`               TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '状态 1:启用，0:禁用',
    `password`                VARCHAR(500)                NOT NULL                             COMMENT '密码',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人',
    `deleted_at`              DATETIME                                                         COMMENT '删除时间',
    `deleted_by`              VARCHAR(50)                                                      COMMENT '删除人',
    `is_deleted`              TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '标记删除 1:未删除，0:已删除'
) COMMENT = '后台账号' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_role` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `name`                    CHAR(255)                   NOT NULL                             COMMENT '名称',
    `code`                    CHAR(255)                   NOT NULL                             COMMENT '编码',
    `is_active`               TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '状态 1:启用，0:禁用',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人'
) COMMENT = '角色' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_menu` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `name`                    CHAR(255)                   NOT NULL                             COMMENT '名称',
    `path`                    VARCHAR(255)                                                     COMMENT '树关系路径',
    `pathname`                VARCHAR(255)                NOT NULL                             COMMENT '组件地址',
    `parent_id`               BIGINT                               DEFAULT 0                   COMMENT '父级ID',
    `icon`                    VARCHAR(255)                                                     COMMENT 'icon图标',
    `sort_order`              INT                                                              COMMENT '排序',
    `is_show`                 TINYINT(0)                           DEFAULT 1                   COMMENT '是否显示 1:显示，0:不显示',
    `type`                    TINYINT(0)                           DEFAULT 1                   COMMENT '菜单类型 0:菜单 1:按钮',
    `is_active`               TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '状态 1:启用，0:禁用',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人'
) COMMENT = '菜单' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_role_menu_mapping` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `menu_id`                 BIGINT                                                           COMMENT '菜单ID',
    `role_id`                 BIGINT                                                           COMMENT '角色ID'
) COMMENT = '角色菜单关联' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`m_hospital` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `name`                    VARCHAR(255)                NOT NULL                             COMMENT '医院名称',
    `code`                    VARCHAR(255)                NOT NULL                             COMMENT '医院编码唯一',
    `description`             VARCHAR(255)                NOT NULL                             COMMENT '医院描述',
    `address`                 VARCHAR(255)                NOT NULL                             COMMENT '医院地址',
    `department`              VARCHAR(255)                NOT NULL                             COMMENT '科室',
    `account_id`              BIGINT                      NOT NULL                             COMMENT '账号ID',
    `is_active`               TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '状态 1:启用，0:禁用',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人',
    `deleted_at`              DATETIME                                                         COMMENT '删除时间',
    `deleted_by`              VARCHAR(50)                                                      COMMENT '删除人',
    `is_deleted`              TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '标记删除 1:未删除，0:已删除'
) COMMENT = '医院信息' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`m_appointment_order` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `hospital_id`             BIGINT                     NOT NULL                              COMMENT '医院id',
    `name`                    VARCHAR(50)                NOT NULL                              COMMENT '姓名',
    `age`                     INT                        NOT NULL                              COMMENT '年龄',
    `mobile`                  VARCHAR(20)                NOT NULL                              COMMENT '手机号',
    `appointment_time`        DATETIME                   NOT NULL                              COMMENT '预约时间',
    `disease`                 VARCHAR(100)               NOT NULL                              COMMENT '诊疗疾病',
    `remark`                  VARCHAR(255)                                                     COMMENT '备注',
    `account_id`              VARCHAR(255)                                                     COMMENT '账号ID',
    `write_off`               TINYINT(0)                 NOT NULL DEFAULT 1                    COMMENT '核销 1:已核销 0:未核销',
    `created_at`              DATETIME                   NOT NULL DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
    `created_by`              VARCHAR(50)                NOT NULL                              COMMENT '创建人',
    `updated_at`              DATETIME                                                         COMMENT '修改时间',
    `updated_by`              VARCHAR(50)                                                      COMMENT '修改人',
    `deleted_at`              DATETIME                                                         COMMENT '删除时间',
    `deleted_by`              VARCHAR(50)                                                      COMMENT '删除人',
    `is_deleted`              TINYINT(0)                  NOT NULL DEFAULT 1                   COMMENT '标记删除 1:未删除，0:已删除',
    INDEX idx_appointment_time (appointment_time)
) COMMENT = '预约列表' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `medical_appointment`.`sys_dict` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `parent_id`               BIGINT                                                           COMMENT '上级id',
    `account_id`              BIGINT                                                           COMMENT '账号id',
    `name`                    VARCHAR(100)               NOT NULL                              COMMENT '字典名',
    `remark`                  VARCHAR(255)                                                     COMMENT '备注',
    `sort_order`              INT                                                              COMMENT '排序',
    `is_active`               TINYINT(0)                 NOT NULL DEFAULT 1                    COMMENT '状态 1:启用，0:禁用',
    `created_at`              DATETIME                   NOT NULL DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
    `created_by`              VARCHAR(50)                NOT NULL                              COMMENT '创建人'
) COMMENT = '字典' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

INSERT INTO `medical_appointment`.`sys_account`
(`id`, `name`, `account`, `mobile`, `email`, `role_code`, `is_active`, `password`, `created_at`, `created_by`, `updated_at`, `updated_by`, `deleted_at`, `deleted_by`, `is_deleted`)
VALUES
(202507311424560001, '系统管理员', 'admin', '13641663423', '514360255@qq.com', 'SUPER_ADMIN', 1, '55f5ac30819e4db8a61d61126dbc5407', '2023-02-20 14:25:10', 'admin', NULL, NULL, NULL, NULL, 1)