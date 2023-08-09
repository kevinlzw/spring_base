CREATE TABLE IF NOT EXISTS `order` (
    `id`               VARCHAR(32)    NOT NULL COMMENT '主键',
    `customer_id`      VARCHAR(32)    NOT NULL COMMENT '客户ID',
    `order_id`         VARCHAR(32)    NOT NULL,
    `product_id`       VARCHAR(32)    NOT NULL,
    `product_name`     VARCHAR(255)   NOT NULL,
    `product_price`    DECIMAL(10, 2),
    `product_quantity` VARCHAR(32)    NOT NULL,
    `create_time`      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER
      SET
      = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
