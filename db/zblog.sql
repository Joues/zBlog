/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : zblog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 06/03/2022 16:06:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父分类id',
  `subscribe` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `id_blog` int(11) NULL DEFAULT NULL COMMENT '博客表外键',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_blog`(`id_blog`) USING BTREE,
  CONSTRAINT `blog_category_ibfk_1` FOREIGN KEY (`id_blog`) REFERENCES `blog_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `blog_id` int(11) NULL DEFAULT NULL COMMENT '博客id（外键）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `create_by` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人',
  `e_mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `update_by` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_comment_ibfk_1`(`blog_id`) USING BTREE,
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES (1, 1, NULL, '一二人', NULL, NULL, NULL, NULL);
INSERT INTO `blog_comment` VALUES (2, 1, NULL, '一二人', NULL, NULL, NULL, NULL);
INSERT INTO `blog_comment` VALUES (3, 2, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for blog_detail
-- ----------------------------
DROP TABLE IF EXISTS `blog_detail`;
CREATE TABLE `blog_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` int(11) NULL DEFAULT NULL COMMENT '外键',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_detail_ibfk_1`(`blog_id`) USING BTREE,
  CONSTRAINT `blog_detail_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_detail
-- ----------------------------
INSERT INTO `blog_detail` VALUES (1, 1, '对于 TreeMap 而言，由于它底层采用一棵”红黑树”来保存集合中的 Entry，这意味这 TreeMap 添加元素、取出元素的性能都比 HashMap 低：当 TreeMap 添加元素时，需要通过循环找到新增 Entry 的插入位置，因此比较耗性能；当从 TreeMap 中取出元素时，需要通过循环才能找到合适的 Entry，也比较耗性能。但 TreeMap、TreeSet 比 HashMap、HashSet 的优势在于：TreeMap 中的所有 Entry 总是按 key 根据指定排序规则保持有序状态，TreeSet 中所有元素总是根据指定排序规则保持有序状态。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (2, 2, '红黑树\r\n红黑树是一种自平衡排序二叉树，树中每个节点的值，都大于或等于在它的左子树中的所有节点的值，并且小于或等于在它的右子树中的所有节点的值，这确保红黑树运行时可以快速地在树中查找和定位的所需节点。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (3, 3, '红黑树\r\n红黑树是一种自平衡排序二叉树，树中每个节点的值，都大于或等于在它的左子树中的所有节点的值，并且小于或等于在它的右子树中的所有节点的值，这确保红黑树运行时可以快速地在树中查找和定位的所需节点。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (4, 4, '对于 TreeMap 而言，由于它底层采用一棵”红黑树”来保存集合中的 Entry，这意味这 TreeMap 添加元素、取出元素的性能都比 HashMap 低：当 TreeMap 添加元素时，需要通过循环找到新增 Entry 的插入位置，因此比较耗性能；当从 TreeMap 中取出元素时，需要通过循环才能找到合适的 Entry，也比较耗性能。但 TreeMap、TreeSet 比 HashMap、HashSet 的优势在于：TreeMap 中的所有 Entry 总是按 key 根据指定排序规则保持有序状态，TreeSet 中所有元素总是根据指定排序规则保持有序状态。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (5, 5, '对于 TreeMap 而言，由于它底层采用一棵”红黑树”来保存集合中的 Entry，这意味这 TreeMap 添加元素、取出元素的性能都比 HashMap 低：当 TreeMap 添加元素时，需要通过循环找到新增 Entry 的插入位置，因此比较耗性能；当从 TreeMap 中取出元素时，需要通过循环才能找到合适的 Entry，也比较耗性能。但 TreeMap、TreeSet 比 HashMap、HashSet 的优势在于：TreeMap 中的所有 Entry 总是按 key 根据指定排序规则保持有序状态，TreeSet 中所有元素总是根据指定排序规则保持有序状态。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (6, 6, '红黑树\r\n红黑树是一种自平衡排序二叉树，树中每个节点的值，都大于或等于在它的左子树中的所有节点的值，并且小于或等于在它的右子树中的所有节点的值，这确保红黑树运行时可以快速地在树中查找和定位的所需节点。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (7, 7, '红黑树\r\n红黑树是一种自平衡排序二叉树，树中每个节点的值，都大于或等于在它的左子树中的所有节点的值，并且小于或等于在它的右子树中的所有节点的值，这确保红黑树运行时可以快速地在树中查找和定位的所需节点。', NULL, NULL, NULL, NULL);
INSERT INTO `blog_detail` VALUES (8, 8, '对于 TreeMap 而言，由于它底层采用一棵”红黑树”来保存集合中的 Entry，这意味这 TreeMap 添加元素、取出元素的性能都比 HashMap 低：当 TreeMap 添加元素时，需要通过循环找到新增 Entry 的插入位置，因此比较耗性能；当从 TreeMap 中取出元素时，需要通过循环才能找到合适的 Entry，也比较耗性能。但 TreeMap、TreeSet 比 HashMap、HashSet 的优势在于：TreeMap 中的所有 Entry 总是按 key 根据指定排序规则保持有序状态，TreeSet 中所有元素总是根据指定排序规则保持有序状态。', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者id（外键）',
  `summary` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章概要',
  `poll_count` bigint(20) NULL DEFAULT NULL COMMENT '点赞数（冗余）',
  `comment_count` bigint(20) NULL DEFAULT NULL COMMENT '评论数（冗余）',
  `read_count` bigint(20) NULL DEFAULT NULL COMMENT '阅读数',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '分类ID（外键）',
  `is_top` tinyint(1) NULL DEFAULT 0 COMMENT '是否置顶',
  `is_essence` tinyint(1) NULL DEFAULT 0 COMMENT '是否热门',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_poll
-- ----------------------------
DROP TABLE IF EXISTS `blog_poll`;
CREATE TABLE `blog_poll`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '点赞时间',
  `blog_id` int(11) NULL DEFAULT NULL COMMENT '博客id（外键）',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `poll` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_poll_ibfk_1`(`blog_id`) USING BTREE,
  CONSTRAINT `blog_poll_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_poll
-- ----------------------------
INSERT INTO `blog_poll` VALUES (1, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (2, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (3, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (4, NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (5, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (6, NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `blog_poll` VALUES (7, NULL, 3, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `blog_id` int(11) NULL DEFAULT NULL COMMENT '外键（博客id）',
  `subscribe` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  CONSTRAINT `blog_tag_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_type` int(11) NULL DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(11) NULL DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `request_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_userid`(`userid`) USING BTREE,
  INDEX `index_logt_ype`(`log_type`) USING BTREE,
  INDEX `index_operate_type`(`operate_type`) USING BTREE,
  INDEX `index_log_type`(`log_type`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('b09ccd219a1ce5c7270bb659748b8330', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 15:34:38', NULL, NULL);
INSERT INTO `sys_log` VALUES ('a22ddd4b5b0b84bd7794edd24b25fc64', 2, '添加测试DEMO', NULL, 'admin', '管理员', '127.0.0.1', 'org.jeecg.modules.demo.test.controller.JeecgDemoController.add()', NULL, '[{\"createBy\":\"admin\",\"createTime\":1547883299809,\"email\":\"zhangdaiscott@163.com\",\"id\":\"7eac655877842eb39dc2f0469f3964ec\",\"name\":\"zhang daihao\"}]', NULL, 25, 'admin', '2019-01-19 15:34:59', NULL, NULL);
INSERT INTO `sys_log` VALUES ('07a0b3f8b4140a7a586305c2f40a2310', 2, '删除测试DEMO', NULL, 'admin', '管理员', '127.0.0.1', 'org.jeecg.modules.demo.test.controller.JeecgDemoController.delete()', NULL, '[\"7eac655877842eb39dc2f0469f3964ec\"]', NULL, 14, 'admin', '2019-01-19 15:38:11', NULL, NULL);
INSERT INTO `sys_log` VALUES ('d7902eeab2c34611fad046a79bff1c1b', 2, '添加测试DEMO', NULL, 'admin', '管理员', '127.0.0.1', 'org.jeecg.modules.demo.test.controller.JeecgDemoController.add()', NULL, '[{\"createBy\":\"admin\",\"createTime\":1547883544104,\"email\":\"zhangdaiscott@163.com\",\"id\":\"4436302a0de50bb83025286bc414d6a9\",\"name\":\"zhang daihao\"}]', NULL, 1682, 'admin', '2019-01-19 15:39:05', NULL, NULL);
INSERT INTO `sys_log` VALUES ('a68160f37cace166fedd299c4ca0be10', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 15:40:00', NULL, NULL);
INSERT INTO `sys_log` VALUES ('c6c0316b6989bf1eea0a3803f593bf69', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 15:47:19', NULL, NULL);
INSERT INTO `sys_log` VALUES ('4b1341863a8fffeccda8bbe413bd815f', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 15:59:52', NULL, NULL);
INSERT INTO `sys_log` VALUES ('ed50b1fbc80c3b953f4551081b10335e', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 16:19:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('dabdcb8e15ea9215a1af22f7567ff73d', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 16:48:13', NULL, NULL);
INSERT INTO `sys_log` VALUES ('446724ea6dd41f4a03111c42e00d80cd', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 16:56:36', NULL, NULL);
INSERT INTO `sys_log` VALUES ('0e41fe3a34d5715bf4c88e220663583a', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 17:04:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('9f2db1ffaf89518a25cc6701da0c5858', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 17:05:07', NULL, NULL);
INSERT INTO `sys_log` VALUES ('954f1ccb8b230d2d7d4858eec3aba0a4', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 17:08:37', NULL, NULL);
INSERT INTO `sys_log` VALUES ('7374f3a2ccb20216cf8eecb26037ce0a', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 18:08:51', NULL, NULL);
INSERT INTO `sys_log` VALUES ('130de55edac71aab730786307cc65936', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 20:22:57', NULL, NULL);
INSERT INTO `sys_log` VALUES ('0bc44e2d682c9f28525d203589a90b43', 1, '用户名: admin,登录成功！', NULL, NULL, NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, 'jeecg-boot', '2019-01-19 20:31:08', NULL, NULL);
INSERT INTO `sys_log` VALUES ('122edcafd54dd06e12838f41123d9d5d', 2, '添加测试DEMO', NULL, 'admin', '管理员', '127.0.0.1', 'org.jeecg.modules.demo.test.controller.JeecgDemoController.add()', NULL, '[{\"age\":28,\"birthday\":1546617600000,\"createBy\":\"admin\",\"createTime\":1547901234989,\"id\":\"42c08b1a2e5b2a96ffa4cc88383d4b11\",\"name\":\"秦500\",\"punchTime\":1546691611000}]', NULL, 21387, 'admin', '2019-01-19 20:34:11', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1316011512896131073', 1, '用户登录失败，用户不存在！', NULL, NULL, NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-13 21:42:50', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1316013787869843457', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-13 21:51:52', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1320356782018584577', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.5', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-25 21:29:23', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1320360159796490242', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.5', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-25 21:42:48', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1320402201557803010', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.5', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-26 00:29:51', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1320403044331888641', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.5', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-26 00:33:12', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1320404564922277890', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.5', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-26 00:39:15', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1321107850155511810', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-27 23:13:51', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1321111542300659713', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-27 23:28:31', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1321111559597969410', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-27 23:28:36', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1321112056715182082', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '2.0.1.20', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-27 23:30:34', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322868262950592513', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 19:49:06', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322868460133212161', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 19:49:53', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322868874501087233', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 19:51:32', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322873224443158530', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 20:08:49', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322913903970054146', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 22:50:28', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1322913941844619266', 1, '用户名: admin,登录成功！', NULL, 'admin', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-01 22:50:37', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323246454471262210', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 20:51:54', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323280781108236289', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:08:18', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323278767829733378', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:00:18', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323271945089634305', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:33:12', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323272082893492226', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:33:44', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323272309293633538', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:34:38', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323276126135439361', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:49:48', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323277445931913218', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:55:03', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323277605063806978', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 22:55:41', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351148190678605825', 2, '', 1, NULL, NULL, '127.0.0.1', 'cn.ityihang.zblog.blog.controller.BlogController.getBlogList()', NULL, '  blog: Blog(id=null, title=null, createdTime=null, updateTime=null, userId=null, summary=null, pollCount=null, commentCount=null, readCount=null, classId=null, isTop=null, isEssence=null)  pageParam: PageParam(pageNo=1, limit=10, keyword=null)  req: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3a3876bc', NULL, 1489, NULL, '2021-01-18 20:43:26', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351144100485570562', 2, '', 1, NULL, NULL, '127.0.0.1', 'cn.ityihang.zblog.blog.controller.BlogController.getBlogList()', NULL, '  blog: Blog(id=null, title=null, createdTime=null, updateTime=null, userId=null, summary=null, pollCount=null, commentCount=null, readCount=null, classId=null, isTop=null, isEssence=null)  pageParam: PageParam(pageNo=1, limit=10, keyword=null)  req: org.apache.shiro.web.servlet.ShiroHttpServletRequest@7cda1f9', NULL, 564, NULL, '2021-01-18 20:27:11', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351144100485570561', 2, '', 1, NULL, NULL, '127.0.0.1', 'cn.ityihang.zblog.blog.controller.BlogController.getBlogList()', NULL, '  blog: Blog(id=null, title=null, createdTime=null, updateTime=null, userId=null, summary=null, pollCount=null, commentCount=null, readCount=null, classId=null, isTop=null, isEssence=null)  pageParam: PageParam(pageNo=1, limit=10, keyword=null)  req: org.apache.shiro.web.servlet.ShiroHttpServletRequest@6ce3a053', NULL, 564, NULL, '2021-01-18 20:27:11', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1325801508649762817', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-09 22:04:47', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323280891233882113', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:08:44', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323282552115372033', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:15:20', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323283031910195202', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:17:15', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323283342313857025', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:18:29', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323283806317125634', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-02 23:20:20', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1323650642916327426', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2020-11-03 23:38:00', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1377107476608049154', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '192.168.109.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-31 03:56:23', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1377105687334739969', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '192.168.109.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-31 03:49:16', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1377104295320649729', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '192.168.109.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-31 03:43:44', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1377077679479713793', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '192.168.109.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-31 01:57:58', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1354088091795480578', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-26 23:25:33', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351162117294534657', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-18 21:38:47', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351153162724012034', 1, '用户名: root,登录成功！', NULL, 'root', NULL, '127.0.0.1', NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-18 21:03:12', NULL, NULL);
INSERT INTO `sys_log` VALUES ('1351148191345500161', 2, '', 1, NULL, NULL, '127.0.0.1', 'cn.ityihang.zblog.blog.controller.BlogController.getBlogList()', NULL, '  blog: Blog(id=null, title=null, createdTime=null, updateTime=null, userId=null, summary=null, pollCount=null, commentCount=null, readCount=null, classId=null, isTop=null, isEssence=null)  pageParam: PageParam(pageNo=1, limit=10, keyword=null)  req: org.apache.shiro.web.servlet.ShiroHttpServletRequest@670a439', NULL, 1666, NULL, '2021-01-18 20:43:27', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL COMMENT '主键ID',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名（账户）',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MD5密码盐',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '数据软删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(11) NULL DEFAULT 1 COMMENT '账户状态',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1000, 'admin', '123456', NULL, 0, NULL, 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1004, 'yihang', 'af289a3f820b302c', '52TNTp9M', 0, '2020-11-02 20:42:56', 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1005, 'root', '475c474bb074d1ad', 'hY4bQCw1', 0, '2020-11-02 20:51:03', 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1006, 'taobao', '832874d65c7a44bb', 'OZA01tlx', 0, '2020-11-09 00:20:22', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_details`;
CREATE TABLE `sys_user_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avator` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `e_mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qr_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `flag` int(11) NULL DEFAULT 0 COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user_id`) USING BTREE,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_details
-- ----------------------------
INSERT INTO `sys_user_details` VALUES (202002001, 1000, '都说酒是陈的香', NULL, 18, '18688886666', '12345@qq.com', NULL, '湖北武汉', 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_details` VALUES (202002002, 1005, '江南', NULL, 24, '13690309822', '2332455@qq.com', NULL, '广东惠州', 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_details` VALUES (202002003, 1006, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `sys_user_id` int(11) NULL DEFAULT NULL COMMENT '用户主键ID',
  `sys_role_id` int(11) NULL DEFAULT NULL COMMENT '角色主键ID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
