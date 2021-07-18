-- neuraxislink.datadic definition

CREATE TABLE `datadic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `groupcode` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '字典项代码',
  `groupname` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '字典项名称',
  `groupdesc` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '字典项描述',
  `creuser_code` int(11) DEFAULT NULL COMMENT '创建者',
  `cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `upduser_code` int(11) DEFAULT NULL COMMENT '更新者',
  `updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) unsigned DEFAULT '0' COMMENT '版本',
  `deleteflag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- neuraxislink.datadicitem definition

CREATE TABLE `datadicitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `datadic_id` int(11) NOT NULL COMMENT '字典项ID',
  `dataitemcode` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '字典项明细代码',
  `dataitemname` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '字典项明细名称',
  `dataitemdesc` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '字典项明细描述',
  `creuser_code` int(11) DEFAULT NULL COMMENT '创建者',
  `cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `upduser_code` int(11) DEFAULT NULL COMMENT '更新者',
  `updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) unsigned DEFAULT '0' COMMENT '版本',
  `deleteflag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- neuraxislink.dept definition

CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '部门代码',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '部门名称',
  `desc` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '部门描述',
  `creuser_code` int(11) DEFAULT NULL COMMENT '创建者',
  `cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `upduser_code` int(11) DEFAULT NULL COMMENT '更新者',
  `updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) unsigned DEFAULT '0' COMMENT '版本',
  `deleteflag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- neuraxislink.emp definition

CREATE TABLE `emp` (
  `EMPNO` bigint(20) NOT NULL,
  `ENAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `JOB` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MGR` bigint(20) DEFAULT NULL,
  `HIREDATE` datetime DEFAULT NULL,
  `SAL` bigint(20) DEFAULT NULL,
  `COMM` bigint(20) DEFAULT NULL,
  `DEPTNO` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `code` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `desc` varchar(7) COLLATE utf8_bin DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `creuser_code` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `cretime` varchar(19) COLLATE utf8_bin DEFAULT NULL,
  `upduser_code` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `updtime` varchar(19) COLLATE utf8_bin DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deleteflag` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- neuraxislink.pricelist definition

CREATE TABLE `pricelist` (
  `classa` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `serial` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `productname` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL,
  `model` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `mar` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `cost` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- neuraxislink.team definition

CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '团队代码',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '团队名称',
  `desc` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '团队描述',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `creuser_code` int(11) DEFAULT NULL COMMENT '创建者',
  `cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `upduser_code` int(11) DEFAULT NULL COMMENT '更新者',
  `updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) unsigned DEFAULT '0' COMMENT '版本',
  `deleteflag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- neuraxislink.users definition

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usercode` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户代码',
  `username` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `password` char(32) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `roles` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '角色',
  `tel` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '电话',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(1) NOT NULL COMMENT '性别: 0 男 ; 1 女',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  `team_id` int(11) DEFAULT NULL COMMENT '团队ID',
  `state` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '状态: 0 在职 ； 1 离职',
  `entrydate` date NOT NULL COMMENT '入职日期',
  `leavedate` date DEFAULT NULL COMMENT '离职时间',
  `creuser_code` int(11) DEFAULT NULL COMMENT '创建者',
  `cretime` datetime DEFAULT NULL COMMENT '创建时间',
  `upduser_code` int(11) DEFAULT NULL COMMENT '更新者',
  `updtime` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) unsigned DEFAULT '0' COMMENT '版本',
  `deleteflag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;



INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(1, '309', '王超华', '21218CCA77804D2BA1922C33E0151105', 'ADMIN', '18610526716', '18610526716@163.com', 0, 29, 22, '0', '2019-09-26', NULL, 309, '2019-09-26 16:55:01', NULL, '2019-10-07 21:20:40', 9, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(19, '310', '张洪朋', '21218CCA77804D2BA1922C33E0151105', 'SM', '15888669988', '15888669988@163.com', 0, 29, 22, '0', '2019-10-07', NULL, NULL, '2019-10-07 22:34:44', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(20, '311', '董艳', '21218CCA77804D2BA1922C33E0151105', 'PO', '13999223311', '13999223311@163.com', 1, 29, 22, '0', '2019-10-07', NULL, NULL, '2019-10-07 22:35:34', NULL, '2019-10-07 22:38:18', 2, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(25, '312', 'MYS', '21218CCA77804D2BA1922C33E0151105', 'PM', '18810042848', '61051537@qq.com', 0, 33, NULL, '0', '2020-04-05', NULL, NULL, '2020-04-05 23:27:07', NULL, NULL, 0, '0');




INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(1, '309', '王超华', '21218CCA77804D2BA1922C33E0151105', 'ADMIN', '18610526716', '18610526716@163.com', 0, 29, 22, '0', '2019-09-26', NULL, 309, '2019-09-26 16:55:01', NULL, '2019-10-07 21:20:40', 9, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(19, '310', '张洪朋', '21218CCA77804D2BA1922C33E0151105', 'SM', '15888669988', '15888669988@163.com', 0, 29, 22, '0', '2019-10-07', NULL, NULL, '2019-10-07 22:34:44', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(20, '311', '董艳', '21218CCA77804D2BA1922C33E0151105', 'PO', '13999223311', '13999223311@163.com', 1, 29, 22, '0', '2019-10-07', NULL, NULL, '2019-10-07 22:35:34', NULL, '2019-10-07 22:38:18', 2, '0');
INSERT INTO neuraxislink.users
(id, usercode, username, password, roles, tel, email, sex, dept_id, team_id, state, entrydate, leavedate, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(25, '312', 'MYS', '21218CCA77804D2BA1922C33E0151105', 'PM', '18810042848', '61051537@qq.com', 0, 33, NULL, '0', '2020-04-05', NULL, NULL, '2020-04-05 23:27:07', NULL, NULL, 0, '0');


INSERT INTO neuraxislink.team
(id, code, name, `desc`, dept_id, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(22, 'XRK', '向日葵', '我们热爱阳光！', 29, NULL, '2019-10-03 08:14:49', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.team
(id, code, name, `desc`, dept_id, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(23, 'BZT', '暴走团', '在路上', 29, NULL, '2019-10-03 08:15:15', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.team
(id, code, name, `desc`, dept_id, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(24, 'TZ', '团子', '软萌可爱又好吃', 29, NULL, '2019-10-03 08:15:36', NULL, '2019-10-03 08:16:05', 1, '0');



INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass', 'SensePass,SPS020-STD,5.5寸屏,单机版', 'SPS020-STD', '300000000087', 8692, 28100);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass', 'SensePass Pro,SPS020-PRO,8寸屏', 'SPS020-PRO', '300000000410', 23700, 6553);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass', 'SensePassPro遮阳罩，SPS020-Cover01', 'SPS020-Cover01', '102000500005', 2173, 2736);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass', 'SensePass X,SPS51HD01,8寸屏,WIFI,三目', 'SPS51HD01', '300000000536', 25600, 27775);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass', 'SensePass C，标准版，SPS31HD01', 'SPS31HD01', '300000000598', 18650, 20574);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass系列支架', 'SensePass立式支架，SPS020-Bracket02', 'SPS020-Bracket02', '201000900001', 5671, 6723);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SensePass系列支架', 'SensePass防水闸机支架', 'SPS51-Bracket03', '201000900003', 2616, 2490);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseID', 'SenseID020，标准版', 'SID020-S', '300000000260', 30607, 37338);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseID', 'SenseID020，专业版，含指纹模块', 'SID020-P', '300000000259', 35378, 39277);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseID', 'SenseID030，单屏版', 'SID030-S', '300000000268', 35710, 38969);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseID', 'SenseID030，双屏版，含OCR识别功能', 'SID030-DS', '300000000266', 41787, 46636);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate010', 'SenseGate-8寸双目 商汤睿行人脸识别一体机（标准型）', 'SGT010-STD-BS8', '300000000458', 92003, 926823);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate010', 'SenseGate010 8寸屏户外人脸识别一体机,标准刷卡型', 'SGT010-STD-BS8N', '300000000457', 9933, 93256);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate系列
支架', '支架，SenseGate闸机支架', 'SGT010-B-Bracket01', '108100400016', 216, 240);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate系列
支架', 'SenseGate壁挂普通支架', 'SGT010-B-Bracket03', '201000900007', 45, 50);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate系列
支架', '支架，SenseGate立式支架', 'SGT010-B-Bracket04', '108100400033', 790, 877);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseGate系列
支架', 'SenseGate盒式壁挂支架（防水）', 'SGT010-B-Bracket05', '201000900008', 130, 144);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseThunder系列', 'SenseThunder-E测温热像仪V1.0 （火神）（测温模组）', 'STE11AR01', '300000000592', 270800, 2970);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseThunder系列', '火神mini测温产品（包含SensePass）', 'SPS020-TKIT', '300000000634', 59200, 57782);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseThunder系列', '声光报警器', 'SPJ21BS01', '201000900004', 1857, 2059);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseKeeper', 'SenseKeeper030,闸机版,单机版', 'SKP030', '300000000090', 27590, 38053);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseEngine', 'SenseEngine AI视觉模组,512MB+128MB,单目,V2 ', 'M10', '300000000441', 7260, 2889);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseEngine', 'SenseEngine AI视觉模组, 512MB+4GB,双目', 'M20', '300000000507', 9415, 4961);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseLink', 'SenseLink工控机，标准性能服务器', 'SL-Server-Std', '108000200098', 642857, 947571);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseLink', 'SenseLink高性能工控机（可支持50路终端接入）', 'SL-PLC-PRO', '108001100002', 911429, 126986);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI ', 'SenseAike', '商汤爱客人脸识别一体机 010 标准版', 'SWE010-STD', '300000000540', 800, 888);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('BI', 'SensePass系列桌面底座', 'SensePass系列桌面底座,SPJ01DZ01', 'SPJ01DZ01', '201000900005', 1770, 1899);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseNebula-M', 'SenseNebula智能边缘节点星云M系列（Nebula-M4 ）', 'ST-SNMA-SC04M1 ', '300000000566', 80070, 88980);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseNebula-M', 'SenseNebula智能边缘节点星云M系列（Nebula-M8）', 'ST-SNMA-SC08M1', '300000000567', 120900, 133720);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseNebula-G（渠道配单工具两卡机）', 'SenseNebula智能边缘节点星云G系列（Nebula-G）', 'ST-NGCA-PP09601', '300000000471', 2526070, 2803896);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseNebula-G（渠道配单工具两卡机）', 'SenseNebula智能边缘节点星云G系列（Nebula-G）', 'ST-NGCA-SP01601', '300000000106', 202200, 224442);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', '星云S系列智能摄像机软件', '星云智能相机臻智系列，200万像素双目摄像机', 'ST-NC-BSG-T2AS08IR1', '300000000600', 62950, 39275);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', 'SenseDLC一体筒型人脸抓拍摄像机', 'ST-DLCC-2F24BFD-IR-J', '108100100038', 6840, 6932);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', 'SenseDLC一体筒型人脸抓拍摄像机', 'ST-DLCC-2E23BFD-IR-J', '108100100039', 91030, 16143);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', 'SenseDLC半球型人脸抓拍摄像机', 'ST-DLCC-2F24DFD-IR-J', '108100100040', 9660, 10966);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', 'SenseDLC半球型人脸抓拍摄像机', 'ST-DLCC-2E23DFD-IR-J', '108100100041', 11365, 19260);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', 'SenseDLC-400万像素人像抓拍护罩一体机', 'ST-DLCP-4S26HFD-LED-T', '300000000454', 25660, 29842);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', '200万人像抓拍一体筒型摄像机', 'ST-DLCP-2G22BFL-LED-D', '108100100053', 12995, 16437);
INSERT INTO neuraxislink.pricelist
(classa, serial, productname, model, mar, cost, price)
VALUES('IDEA', 'SenseDLC', '400万人像识别一体筒型摄像机', 'ST-DLCP-4L22BAS-LED-D', '108100100059', 29425, 32247);



INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7369, 'SMITH22', 'CLERK22', 7902, '1980-12-17 00:00:00', 900, NULL, 20);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20 00:00:00', 1600, 300, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22 00:00:00', 1250, 500, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7566, 'JONES', 'MANAGER', 7839, '1981-04-02 00:00:00', 2975, NULL, 20);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28 00:00:00', 1250, 1400, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01 00:00:00', 2850, NULL, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09 00:00:00', 2450, NULL, 10);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19 00:00:00', 3000, NULL, 20);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17 00:00:00', 5000, NULL, 10);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08 00:00:00', 1500, 0, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7876, 'ADAMS', 'CLERK', 7788, '1987-05-23 00:00:00', 1100, NULL, 20);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7900, 'JAMES', 'CLERK', 7698, '1981-12-03 00:00:00', 950, NULL, 30);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7902, 'FORD', 'ANALYST', 7566, '1981-12-03 00:00:00', 3000, NULL, 20);
INSERT INTO neuraxislink.emp
(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES(7934, 'MILLER', 'CLERK', 7782, '1982-01-23 00:00:00', 1300, NULL, 10);


INSERT INTO neuraxislink.dept
(id, code, name, `desc`, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(29, 'DEV1', '研发一部', '研发一部负责一汽丰田项目研发', NULL, '2019-09-29 22:14:13', NULL, '2019-10-03 16:23:04', 1, '0');
INSERT INTO neuraxislink.dept
(id, code, name, `desc`, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(30, 'DEV2', '研发二部', '研发二部负责北京现代项目开发', NULL, '2019-09-29 22:15:13', NULL, '2019-10-03 16:22:51', 1, '0');
INSERT INTO neuraxislink.dept
(id, code, name, `desc`, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(31, 'RLZY', '人力资源部', '', NULL, '2019-09-29 22:16:12', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.dept
(id, code, name, `desc`, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(32, 'CW', '财务部', '', NULL, '2019-09-29 22:16:46', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.dept
(id, code, name, `desc`, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(33, 'YW', '运维部', '', NULL, '2019-09-29 22:17:12', NULL, NULL, 0, '0');





INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(25, 21, '0', '在职', '在职', NULL, '2019-09-29 21:29:18', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(26, 21, '1', '离职', '离职', NULL, '2019-09-29 21:29:26', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(27, 22, 'ADMIN', 'System Administrator', '系统管理员', NULL, '2019-09-29 21:30:27', NULL, '2019-10-03 19:41:27', 2, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(28, 22, 'PM', 'Project Managers', '项目经理', NULL, '2019-09-29 21:31:34', NULL, '2019-10-03 19:40:16', 1, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(29, 22, 'PO', 'Product Owner', '产品负责人', NULL, '2019-09-29 21:32:46', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(30, 22, 'SM', 'Scrum Master', '组织负责人', NULL, '2019-09-29 21:33:49', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(31, 22, 'DTM', 'Dev Team Member', '开发团队成员', NULL, '2019-09-29 21:36:17', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(32, 23, 'P01', '前期跟进', '前期跟进', NULL, '2019-10-06 11:42:44', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(33, 23, 'P02', '合同签订', '合同签订', NULL, '2019-10-06 11:42:56', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(34, 23, 'P03', '要件中', '要件中', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(35, 23, 'P04', '要件完成', '要件完成', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(36, 23, 'P05', '要件签字', '要件签字', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(37, 23, 'P06', '实施中', '实施中', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(38, 23, 'P07', '实施完成', '实施完成', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(39, 23, 'P08', '验收中', '验收中', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(40, 23, 'P09', '验收完成', '验收完成', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(41, 23, 'P10', '完成交付', '完成交付', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(42, 23, 'P11', '回款完毕', '回款完毕', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(43, 23, 'P12', '暂停', '暂停', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(44, 23, 'P13', '终止', '终止', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadicitem
(id, datadic_id, dataitemcode, dataitemname, dataitemdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(45, 23, 'P14', '正常关闭', '正常关闭', NULL, '2019-10-06 11:48:43', NULL, NULL, 0, '0');



INSERT INTO neuraxislink.datadic
(id, groupcode, groupname, groupdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(21, 'ServiceStatus', '在职状态', '在职状态', NULL, '2019-09-29 21:27:48', NULL, NULL, 0, '0');
INSERT INTO neuraxislink.datadic
(id, groupcode, groupname, groupdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(22, 'Role', '角色', '角色', NULL, '2019-09-29 21:29:58', NULL, '2019-10-03 16:23:51', 1, '0');
INSERT INTO neuraxislink.datadic
(id, groupcode, groupname, groupdesc, creuser_code, cretime, upduser_code, updtime, version, deleteflag)
VALUES(23, 'ProjectStatus', '项目状态', '项目状态', NULL, '2019-10-06 11:32:22', NULL, NULL, 0, '0');




