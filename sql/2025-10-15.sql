
# 新增预约医院专家表
CREATE TABLE `medical_appointment`.`m_appointment_hospital_expert` (
    `id`                      BIGINT                                               PRIMARY KEY COMMENT '主键',
    `hospital_id`             BIGINT                      NOT NULL                             COMMENT '医院ID',
    `name`                    VARCHAR(50)                 NOT NULL                             COMMENT '姓名',
    `intro`                   VARCHAR(255)                NOT NULL                             COMMENT '简介',
    `domain`                  VARCHAR(255)                NOT NULL                             COMMENT '领域',
    `title`                   VARCHAR(100)                NOT NULL                             COMMENT '职称'
) COMMENT = '预约医院专家' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 医院表在 `consultation_hours` 字段后新增 `医院介绍主图` 字段
ALTER TABLE m_hospital ADD COLUMN `intro_pic` VARCHAR(255) NOT NULL COMMENT '医院介绍主图' AFTER `consultation_hours`;

# 医院表在 `env_pic` 字段后新增 `医院环境图` 字段
ALTER TABLE m_hospital ADD COLUMN `env_pic` TEXT NOT NULL COMMENT '医院环境图' AFTER `intro_pic`;