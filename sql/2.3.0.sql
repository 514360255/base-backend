
# 医院表在 `title` 字段后新增 `排序` 字段
ALTER TABLE m_appointment_hospital_expert ADD COLUMN `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序' AFTER `title`;