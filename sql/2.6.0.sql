
# 医院专家表在 `sort_order` 字段后新增 `接诊时间` 字段
ALTER TABLE m_appointment_hospital_expert ADD COLUMN `consultation_hours` VARCHAR(255) COMMENT '接诊时间' AFTER `sort_order`;

# 医院表在 `ext` 字段后新增 `是否展示医生栏目` 字段
ALTER TABLE m_hospital ADD COLUMN `is_show_doctor_column` INT DEFAULT 1 COMMENT '是否展示医生栏目' AFTER `ext`;