
# 预约列表 `write_off` 字段后新增 `是否到诊` 字段
ALTER TABLE m_appointment_order ADD COLUMN `is_visit` TINYINT COMMENT '是否到诊 1：已到诊 0：未到诊' AFTER `write_off`;

# 预约列表 `is_visit` 字段后新增 `创建时间` 字段
# ALTER TABLE m_appointment_order ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `is_visit`;

# 预约列表 `created_by` 字段后新增 `创建人` 字段
# ALTER TABLE m_appointment_order ADD COLUMN `created_by` VARCHAR(50) DEFAULT '用户提交' COMMENT '创建人' AFTER `created_at`;