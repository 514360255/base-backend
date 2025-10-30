
# 预约列表 `write_off` 字段后新增 `是否到诊` 字段
ALTER TABLE m_appointment_order ADD COLUMN `is_visit` TINYINT COMMENT '是否到诊 1：已到诊 0：未到诊' AFTER `write_off`;