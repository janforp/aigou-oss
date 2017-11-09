INSERT INTO oss_role (role_id, role_name, role_desc, update_time_ms, create_time_ms) VALUES
(1000,'管理员','管理员',UNIX_TIMESTAMP()*1000,UNIX_TIMESTAMP()*1000);

INSERT INTO oss_admin (admin_id, role_id, real_name, login_name, login_pwd, cellphone, email, portrait, status, update_time_ms, create_time_ms) VALUES
(10000,1000,'超级管理员','system','96df681a32aec2a3b4ffeacdb8aaaf4c', /*密码：123456*/'','','', 1, UNIX_TIMESTAMP()*1000,UNIX_TIMESTAMP()*1000);

INSERT INTO oss_menu_module (module_id, module_name, icon, order_by) VALUES
('1001', '帐号管理', 'user', 5),
('1002', '角色管理', 'regular-group', 5),
('1003', '晒单管理', 'list-alt', 5),
('1004', '发货管理', 'list-alt', 5),
('1005', '渠道统计', 'list-alt', 5),
('1006', '查找', 'list-alt',5),
('1007', '生成帐号', 'list-alt',5),
('1008', '系统配置', 'list-alt',5),
('1009', '商品管理', 'list-alt',5);


INSERT INTO oss_menu_function (function_id, module_id, function_name, function_desc, request_uri, related_request_uri, focus_function_id, show_in_menu, order_by) VALUES
(100101, 1001, '帐号列表', '帐号列表', '/c/page/console/auth/account/list', NULL, NULL, 1, 5),
(100102, 1001, '新建帐号', '新建帐号', '/c/page/console/auth/account/save', NULL, 100101, 0, 5),
(100103, 1001, '修改帐号', '修改帐号', '/c/page/console/auth/account/edit', '/c/page/console/auth/account/update', 100101, 0, 5),
(100104, 1001, '删除帐号', '删除帐号', '/c/page/console/auth/account/remove', NULL, 100101, 0, 5),
(100201, 1002, '角色列表', '角色列表', '/c/page/console/auth/role/list', NULL, NULL, 1, 5),
(100202, 1002, '新建角色', '新建角色', '/c/page/console/auth/role/add', '/c/page/console/auth/role/save', 100201, 0, 5),
(100203, 1002, '修改角色', '修改角色', '/c/page/console/auth/role/edit', '/c/page/console/auth/role/update', 100201, 0, 5),
(100204, 1002, '删除角色', '删除角色', '/c/page/console/auth/role/remove', NULL, 100201, 0, 5),
(100301, 1003, '晒单管理', '晒单管理', '/c/page/console/auth/order/list', NULL, NULL, 1, 5),
(100401, 1004, '中奖发货列表', '中奖发货列表', '/c/page/console/auth/lottery/lotterylist', NULL, NULL, 1, 5),
(100501, 1005, '渠道统计', '渠道统计', '/c/page/console/auth/statistics/moneyAndUser', NULL, NULL, 1, 5),
(100601, 1006, '查找用户', '查找用户', '/c/page/console/auth/buyAndRecharge/findUser', NULL, NULL, 1, 5),
(100602, 1006, '充值纪录', '充值纪录', '/c/page/console/auth/buyAndRecharge/findRecharge', NULL, NULL, 1, 5),
(100603, 1006, '购买纪录', '购买纪录', '/c/page/console/auth/buyAndRecharge/findBuy', NULL, NULL, 1, 5),
(100701, 1007, '生成帐号', '生成帐号', '/c/page/console/auth/innerUsers/createInnerUsers', NULL, NULL, 1, 5),
(100801, 1008, '支付管理', '全部支付', '/c/page/console/auth/pay/payList', NULL, NULL, 1, 5),
(100802, 1008, '推荐', '推荐', '/c/page/console/auth/recommend/recommend', NULL, NULL, 1, 5),
(100803, 1008, '允许中奖', '允许中奖', '/c/page/console/auth/allow/allow', NULL, NULL, 1, 5),
(100804, 1008, 'banner列表', 'banner列表', '/c/page/console/auth/banner/bannerList', NULL, NULL, 1, 5),
(100901, 1009, '添加新商品', '添加新商品', '/c/page/console/auth/goods/addNewGoodsPage', NULL, NULL, 1, 5),
(100902, 1009, '商品列表', '商品列表', '/c/page/console/auth/goods/goodsList', NULL, NULL, 1, 5),
(100903, 1009, '品牌管理', '品牌管理', '/c/page/console/auth/goods/brandList', NULL, NULL, 0, 5),
(100904, 1009, '栏目管理', '栏目管理', '/c/page/console/auth/category/categoryList', NULL, NULL, 1, 5);




INSERT INTO oss_rel_role_menu_function (role_id, function_id) VALUES
(1000, 100101),
(1000, 100102),
(1000, 100103),
(1000, 100104),
(1000, 100201),
(1000, 100202),
(1000, 100203),
(1000, 100204),
(1000, 100301),
(1000, 100401),
(1000, 100501),
(1000, 100601),
(1000, 100602),
(1000, 100603),
(1000, 100701),
(1000, 100801),
(1000, 100802),
(1000, 100803),
(1000, 100804),
(1000, 100901),
(1000, 100902),
(1000, 100903),
(1000, 100904);









