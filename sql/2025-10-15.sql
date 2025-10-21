
# 新增预约医院专家表
CREATE TABLE `medical_appointment`.`m_appointment_hospital_expert` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `hospital_id`             BIGINT                      NOT NULL                             COMMENT '医院ID',
    `account_id`              BIGINT                      NOT NULL                             COMMENT '账号ID',
    `expert_pic`              VARCHAR(255)                NOT NULL                             COMMENT '专家头像',
    `name`                    VARCHAR(50)                 NOT NULL                             COMMENT '姓名',
    `intro`                   VARCHAR(255)                NOT NULL                             COMMENT '简介',
    `domain`                  VARCHAR(255)                NOT NULL                             COMMENT '领域',
    `title`                   VARCHAR(100)                NOT NULL                             COMMENT '职称',
    `created_at`              DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    `created_by`              VARCHAR(50)                 NOT NULL                             COMMENT '创建人'
) COMMENT = '预约医院专家' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 医院表在 `consultation_hours` 字段后新增 `医院介绍主图` 字段
ALTER TABLE m_hospital ADD COLUMN `intro_pic` VARCHAR(255) NOT NULL COMMENT '医院介绍主图' AFTER `consultation_hours`;

# 医院表在 `intro_pic` 字段后新增 `医院环境图` 字段
ALTER TABLE m_hospital ADD COLUMN `env_pic` TEXT NOT NULL COMMENT '医院环境图' AFTER `intro_pic`;

# 医院表在 `env_pic` 字段后新增 `收件人` 字段
ALTER TABLE m_hospital ADD COLUMN `recipient` TEXT NOT NULL COMMENT '收件人' AFTER `env_pic`;

# 医院表在 `recipient` 字段后新增 `额外字段` 字段
ALTER TABLE m_hospital ADD COLUMN `ext` TEXT COMMENT '额外字段' AFTER `recipient`;

# 预约列表在 `env_pic` 字段后新增 `预约专家` 字段
ALTER TABLE m_appointment_order ADD COLUMN `expert` VARCHAR(20) NOT NULL COMMENT '预约专家' AFTER `appointment_time`;

# 预约列表在 `password` 字段后新增 `联系电话` 字段
ALTER TABLE sys_account ADD COLUMN `phone` VARCHAR(20) NOT NULL COMMENT '联系电话' AFTER `password`;