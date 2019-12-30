CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `user_nickName` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `id_card` varchar(19) COLLATE utf8_bin DEFAULT NULL COMMENT '用户身份证号码',
  `user_head` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `wechat` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '微信',
  `gender` int(2) DEFAULT NULL COMMENT '性别：1 男 2 女',
  `mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '用户手机号',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地址',
  `id_card_x` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证正面',
  `id_card_y` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证反面',
  `user_status` int(1) DEFAULT NULL COMMENT '用户状态：1冻结，2正常',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型：1个人 2 公司',
  `user_level` int(11) DEFAULT NULL COMMENT '用户等级',
  `emergency_contact_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '紧急联系人',
  `emergency_contact_mobile` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '紧急联系人手机号',
  `founder` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_type_id` int(11) DEFAULT NULL COMMENT '项目类型ID',
  `project_type_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '项目类型名称',
  `create_user_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人姓名',
  `project_title` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '项目标题',
  `project_content` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目简述',
  `project_min_price` decimal(10,0) DEFAULT NULL COMMENT '项目价格min',
  `project_max_price` decimal(10,0) DEFAULT NULL COMMENT '项目价格max',
  `project_status` int(11) DEFAULT NULL COMMENT '项目状态： 1 进行中  2 结束',
  `examine_status` int(11) DEFAULT NULL COMMENT '审核状态 1待审核 2 已通过 3 已驳回',
  `project_need_people` int(11) DEFAULT NULL COMMENT '项目需要人数',
  `is_direct_hire` int(11) DEFAULT NULL COMMENT '是否直接雇佣：1 是 2 否',
  `is_trusteeship` int(11) DEFAULT NULL COMMENT '项目是否托管： 1 托管 2 不托管，3 已付定金',
  `trusteeship_amount` decimal(10,0) DEFAULT NULL COMMENT '项目托管金额',
  `rank_field` int(11) DEFAULT NULL COMMENT '排名次',
  `project_create_date` datetime DEFAULT NULL COMMENT '项目创建时间',
  `project_end_date` datetime DEFAULT NULL COMMENT '项目截至时间',
  `sort_field` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '排序字段',
  `sort` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '排序：1.升序 2降序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单id,使用规则生成',
  `business_id` int(11) DEFAULT NULL COMMENT '商户ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `project_type_id` int(11) DEFAULT NULL COMMENT '项目类型id',
  `project_type_name` varchar(32) DEFAULT NULL COMMENT '项目类型名称',
  `project_amount` decimal(10,0) DEFAULT NULL COMMENT '项目金额',
  `order_amount` decimal(10,0) DEFAULT NULL COMMENT '订单价格',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态:1待开始 2进行中 3交易成功 4订单取消[用户] 5订单关闭[商家]',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原因',
  `order_date` datetime DEFAULT NULL COMMENT '订单创建时间',
  `order_pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `activity_img` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '活动图片url',
  `rank_field` int(11) DEFAULT NULL COMMENT '排次',
  `banner_img_status` int(11) DEFAULT NULL COMMENT '图片状态：1 正常 2失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


