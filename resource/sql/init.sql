DROP TABLE IF EXISTS `tb_zeus_executor`;
CREATE TABLE `tb_zeus_executor`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `application_code` varchar(255) NULL COMMENT '应用编码',
    `application_name` varchar(255) NULL COMMENT '应用名字',
    `description`      text NULL COMMENT '应用描述',
    `host`             text NOT NULL DEFAULT '[]' COMMENT '应用host信息，[]',
    `check_time`       bigint(20) NOT NULL DEFAULT 0 COMMENT '心跳检查时间，0：不检查',
    `task_id`          text NOT NULL DEFAULT '[]' COMMENT '调度任务ids,[]',
    `updater`          varchar(100)  DEFAULT NULL COMMENT '近期修改用户',
    `create_time`      timestamp NULL COMMENT '创建时间',
    `update_time`      timestamp NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='调度执行器表';


DROP TABLE IF EXISTS `tb_zeus_task`;
CREATE TABLE `tb_zeus_task`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `task_name`   varchar(255) NULL COMMENT '调度任务名',
    `task_corn`   varchar(255) NULL COMMENT '调度任务时间表达式',
    `description` text NULL COMMENT '调度任务描述',
    `executor_id` bigint(20) NULL COMMENT '调度执行器id',
    `job_id`      text NOT NULL DEFAULT '' COMMENT '执行任务ids,[]',
    `creator`     varchar(100)  DEFAULT NULL COMMENT '创建用户',
    `updater`     varchar(100)  DEFAULT NULL COMMENT '近期修改用户',
    `create_time` timestamp NULL COMMENT '创建时间',
    `update_time` timestamp NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='调度任务表';


DROP TABLE IF EXISTS `tb_zeus_job`;
CREATE TABLE `tb_zeus_job`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `job_name`    varchar(255) NULL COMMENT '执行任务名',
    `job_url`     varchar(255) NULL COMMENT '执行任务url',
    `job_header`  longtext NULL COMMENT '执行任务头信息，json串',
    `job_body`    longtext NULL COMMENT '执行任务请求体，json串',
    `job_state`   int NOT NULL DEFAULT 0 COMMENT '任务启动状态，0：禁用，1：起用',
    `description` text NULL COMMENT '执行任务描述',
    `task_id`     bigint(20) NOT NULL COMMENT '调度任务id',
    `creator`     varchar(100) DEFAULT NULL COMMENT '创建用户',
    `updater`     varchar(100) DEFAULT NULL COMMENT '近期修改用户',
    `create_time` timestamp NULL COMMENT '创建时间',
    `update_time` timestamp NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='执行任务表';

DROP TABLE IF EXISTS `tb_zeus_job_task_info`;
CREATE TABLE `tb_zeus_job_task_info`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `job_id`          varchar(255) NULL COMMENT '执行任务名',
    `retry_time`      int NULL COMMENT '重试次数',
    `over_time`       long NULL COMMENT '超时时间',
    `compensate_flag` int NULL COMMENT '补偿标签，0：不补偿，1：补偿',
    `policy`          int NOT NULL DEFAULT 0 COMMENT '调用策略 0：随机 1：轮询',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='执行任务调度信息表';







