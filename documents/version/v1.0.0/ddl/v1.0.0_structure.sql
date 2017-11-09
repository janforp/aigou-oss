DROP TABLE IF EXISTS  `ag_channel_statistics`;
CREATE TABLE `ag_channel_statistics` (
  id                  BIGINT UNSIGNED AUTO_INCREMENT,
  package_name        VARCHAR(100) NOT NULL COMMENT '包名',
  channel_name        VARCHAR(100) NOT NULL COMMENT '渠道名',
  day_people          INT(10) NOT NULL COMMENT '当日新增人数',
  day_recharge        DECIMAL(10,2) NOT NULL COMMENT '当日新增充值',
  day_time            VARCHAR(10) NOT NULL COMMENT '日期',
  PRIMARY KEY(id)
) ENGINE=INNODB COMMENT='渠道统计';


DROP TABLE IF EXISTS  `ag_channel_relation`;
CREATE TABLE `ag_channel_relation` (
  package_name        VARCHAR(100) NOT NULL COMMENT '包名',
  channel_name        VARCHAR(100) NOT NULL COMMENT '渠道名',
  relation_name       VARCHAR(100) NOT NULL COMMENT '关系名',
  PRIMARY KEY(package_name, channel_name)
) ENGINE=INNODB COMMENT='渠道关系';


DROP TABLE IF EXISTS oss_role;
CREATE TABLE oss_role(
  role_id							  INT UNSIGNED AUTO_INCREMENT COMMENT '角色ID，主键，自增长ID',
  role_name					    VARCHAR(64) NOT NULL COMMENT '角色名',
  role_desc 				    VARCHAR(128) COMMENT '角色描述',
  update_time_ms        BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  create_time_ms        BIGINT NOT NULL COMMENT '创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1001 COMMENT='管理员角色表';


DROP TABLE IF EXISTS oss_admin;
CREATE TABLE oss_admin(
  admin_id							INT UNSIGNED AUTO_INCREMENT COMMENT '管理员ID，主键，自增长ID',
  role_id							  INT NOT NULL COMMENT '角色ID',
  real_name					    VARCHAR(64) NOT NULL COMMENT '姓名',
  login_name					  VARCHAR(64) UNIQUE NOT NULL COMMENT '登录名；唯一／非空',
  login_pwd						  VARCHAR(64) NOT NULL COMMENT '登录密码',
  cellphone			        VARCHAR(16) UNIQUE NOT NULL COMMENT '登录手机；唯一／可空',
  email									VARCHAR(64) UNIQUE COMMENT '登录邮箱；唯一／可空',
  portrait							VARCHAR(128) NOT NULL COMMENT '头像文件名',
  status								TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0：禁用；1：正常',
  update_time_ms        BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  create_time_ms        BIGINT NOT NULL COMMENT '创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (admin_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='管理员表';


DROP TABLE IF EXISTS oss_menu_module;
CREATE TABLE oss_menu_module(
  module_id             INT UNSIGNED NOT NULL COMMENT '模块ID',
  module_name           VARCHAR(64) NOT NULL COMMENT '模块名称',
  icon                  VARCHAR(64) NOT NULL COMMENT 'glyphicon icon，如：glyphicon-cog，则icon=cog',
  order_by							TINYINT(3) UNSIGNED DEFAULT 5 COMMENT '排序，值越小，越靠前',
  PRIMARY KEY (module_id)
) ENGINE=InnoDB COMMENT='所有的功能模块表';


DROP TABLE IF EXISTS oss_menu_function;
CREATE TABLE oss_menu_function(
  function_id           INT UNSIGNED NOT NULL COMMENT '功能ID(主键)',
  module_id             INT NOT NULL COMMENT '所属的模块ID',
  request_uri           VARCHAR(128) UNIQUE NOT NULL COMMENT '访问路径（不含参数，唯一）',
  related_request_uri		VARCHAR(128) COMMENT '关联的访问路径（不含参数，可空），如添加账号页面add和添加账号保存save，就只需要在add记录中的related_request_uri设置为save',
  focus_function_id     INT COMMENT '当访问此功能，时要让菜单定位到的功能ID；如：当前访问“添加角色：/role/add”页面，则希望菜单自动定位到“角色列表：/role/list”这个菜单上',
  function_name         VARCHAR(64) NOT NULL COMMENT '功能名称',
  function_desc         VARCHAR(128) COMMENT '功能描述',
  show_in_menu					TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否出现在菜单中。1：是；0：否',
  order_by							TINYINT(3) UNSIGNED DEFAULT 5 COMMENT '排序，值越小，越靠前',
  PRIMARY KEY (function_id)
) ENGINE=InnoDB COMMENT='所有的功能表';


DROP TABLE IF EXISTS oss_rel_role_menu_function;
CREATE TABLE oss_rel_role_menu_function (
  role_id				      INT NOT NULL COMMENT '角色ID',
  function_id         INT NOT NULL COMMENT '功能ID',
  PRIMARY KEY (role_id,function_id)
) ENGINE=InnoDB COMMENT='角色所具备的权限关系表';


DROP TABLE IF EXISTS ag_shop_allow_gain;
CREATE TABLE ag_shop_allow_gain(
  shop_id             INT(10) NOT NULL COMMENT '同类商品唯一的id',
  shop_title          VARCHAR(128) NOT NULL COMMENT '商品标题',
  PRIMARY KEY (shop_id)
) ENGINE=InnoDB COMMENT='允许中奖商品';


DROP TABLE IF EXISTS ag_recommend_shop;
CREATE TABLE ag_recommend_shop(
  shop_id             INT(10) NOT NULL COMMENT '同类商品唯一的id',
  shop_title          VARCHAR(128) NOT NULL COMMENT '商品标题',
  PRIMARY KEY (shop_id)
) ENGINE=InnoDB COMMENT='推荐商品';


DROP TABLE IF EXISTS ag_inner_account;
CREATE TABLE ag_inner_account(
  user_id             INT(10) NOT NULL COMMENT '用户id',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB COMMENT='内部账户';


DROP TABLE IF EXISTS ag_param_config;
CREATE TABLE ag_param_config(
  param_type          VARCHAR(30) NOT NULL COMMENT '参数类型',
  param_name          VARCHAR(64) NOT NULL COMMENT '参数名称',
  first_value         VARCHAR(10) NULL COMMENT '参数值1',
  second_value        VARCHAR(10) NULL COMMENT '参数值2',
  PRIMARY KEY (param_type)
) ENGINE=InnoDB COMMENT='参数配置表';


DROP TABLE IF EXISTS ag_random_times;
CREATE TABLE ag_random_times(
  buy_times          INT(8) NOT NULL COMMENT '购买数',
  PRIMARY KEY (buy_times)
) ENGINE=InnoDB COMMENT='随机购买数表';


-- DROP TABLE IF EXISTS ag_red_envelopes_config;
-- CREATE TABLE ag_red_envelopes_config(
--   red_id            INT UNSIGNED AUTO_INCREMENT COMMENT '红包ID，主键，自增长ID',
--   money_minus       INT UNSIGNED NOT NULL COMMENT '减去金额(元)',
--   money_limit       INT UNSIGNED NOT NULL COMMENT '金额限制(元)',
--   day_limit         INT UNSIGNED NOT NULL COMMENT '使用期限(天)',
--   red_status        TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0：禁用；1：正常',
--   PRIMARY KEY (red_id)
-- ) ENGINE=InnoDB COMMENT='红包配置';

DROP TABLE IF EXISTS ag_category;
CREATE TABLE `ag_category` (
  `cateid` int(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '栏目id',
  `name` varchar(255) DEFAULT NULL COMMENT '栏目名称',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `order` int(6) unsigned DEFAULT '1' COMMENT '排序',
  PRIMARY KEY (`cateid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='栏目表'

