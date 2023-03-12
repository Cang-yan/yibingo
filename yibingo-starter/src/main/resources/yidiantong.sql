DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    union_id VARCHAR(255)    COMMENT '平台union_id' ,
    open_id VARCHAR(255)    COMMENT '平台OpenId' ,
    name VARCHAR(255)    COMMENT '姓名' ,
    head VARCHAR(255)    COMMENT '头像' ,
    download_num INT    COMMENT '剩余可下载次数' ,
    PRIMARY KEY (id)
)  COMMENT = '用户表';

DROP TABLE IF EXISTS vip;
CREATE TABLE vip(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    status INT(1)    COMMENT '是否是会员' ,
    download_add INT    COMMENT '每月增加的下载次数' ,
    expire_time VARCHAR(255)    COMMENT '过期时间' ,
    user_id VARCHAR(32) NOT NULL   COMMENT '用户Id' ,
    PRIMARY KEY (id)
)  COMMENT = '会员权益表';

DROP TABLE IF EXISTS tuple;
CREATE TABLE tuple(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    create_user_id VARCHAR(32) NOT NULL   COMMENT '创建人Id' ,
    organization_id VARCHAR(32) NOT NULL   COMMENT '群组Id' ,
    title VARCHAR(255)    COMMENT '元组名称' ,
    num INT   DEFAULT 1 COMMENT '人数' ,
    PRIMARY KEY (id)
)  COMMENT = '元组';

DROP TABLE IF EXISTS tuple_member;
CREATE TABLE tuple_member(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    organization_id VARCHAR(32) NOT NULL   COMMENT '群组Id' ,
    tuple_id VARCHAR(32) NOT NULL   COMMENT '元组Id' ,
    user_id VARCHAR(32) NOT NULL   COMMENT '元组成员id' ,
    name VARCHAR(255)    COMMENT '元组成员名字' ,
    is_fake INT(1)   DEFAULT 0 COMMENT '是否是虚账户' ,
    head VARCHAR(255)    COMMENT '头像' ,
    PRIMARY KEY (id)
)  COMMENT = '元组成员信息';

DROP TABLE IF EXISTS img_health_code;
CREATE TABLE img_health_code(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    form_records_id VARCHAR(32) NOT NULL   COMMENT '表单记录Id' ,
    url VARCHAR(255)    COMMENT '图片url' ,
    color VARCHAR(255)    COMMENT '颜色' ,
    acid_type VARCHAR(255)    COMMENT '核酸类型' ,
    acid_time VARCHAR(255)    COMMENT '核酸时间' ,
    vaccines_count VARCHAR(32)    COMMENT '疫苗针次' ,
    status INT(1)   DEFAULT 1 COMMENT '是否异常 1或者2' ,
    PRIMARY KEY (id)
)  COMMENT = '健康码图片';

DROP TABLE IF EXISTS img_travel_card;
CREATE TABLE img_travel_card(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    form_records_id VARCHAR(32) NOT NULL   COMMENT '表单记录id' ,
    url VARCHAR(255)    COMMENT '图片url' ,
    color VARCHAR(255)    COMMENT '颜色' ,
    is_star INT(1)   DEFAULT 1 COMMENT '是否带星  1或者2' ,
    travel_records VARCHAR(255)    COMMENT '行程记录' ,
    risk_area VARCHAR(255)    COMMENT '风险地区' ,
    PRIMARY KEY (id)
)  COMMENT = '行程卡照片';

DROP TABLE IF EXISTS organization;
CREATE TABLE organization(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    title VARCHAR(32)    COMMENT '群组命名' ,
    create_user_id VARCHAR(32) NOT NULL   COMMENT '创建人id' ,
    type INT    COMMENT '群组类型，枚举' ,
    notes VARCHAR(255)    COMMENT '备注' ,
    head VARCHAR(255)    COMMENT '群头像' ,
    PRIMARY KEY (id)
)  COMMENT = '群组';

DROP TABLE IF EXISTS form_label;
CREATE TABLE form_label(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    title VARCHAR(255)    COMMENT '标签名称不能重名' ,
    PRIMARY KEY (id)
)  COMMENT = '表单标签';

DROP TABLE IF EXISTS form;
CREATE TABLE form(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    create_user_id VARCHAR(32) NOT NULL   COMMENT '创建人Id' ,
    is_end INT(1)   DEFAULT 0 COMMENT '是否结束，0是没结束 1是结束' ,
    title VARCHAR(255)    COMMENT '表单标题' ,
    notes VARCHAR(255)    COMMENT '备注' ,
    label_combination JSON    COMMENT '交文本标签组合方式' ,
    label_combination_img JSON    COMMENT '交图片的标签组合' ,
    healthy_code INT(1)    COMMENT '是否要健康码' ,
    travel_card INT(1)    COMMENT '是否要行程卡' ,
    organization_id VARCHAR(32) NOT NULL   COMMENT '选择群组id' ,
    begin_time DATETIME    COMMENT '开始时间' ,
    end_time DATETIME    COMMENT '结束时间' ,
    attention_mode INT    COMMENT '提醒方式，枚举' ,
    acid_requirement INT    COMMENT '核酸要求' ,
    schedule_remind INT    COMMENT '定时任务的剩余提醒次数' ,
    PRIMARY KEY (id)
)  COMMENT = '表单发布';

DROP TABLE IF EXISTS form_img;
CREATE TABLE form_img(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL   COMMENT '创建时间' ,
    update_time DATETIME NOT NULL   COMMENT '更新时间' ,
    healthy_code INT(1)    COMMENT '是否要健康码' ,
    tarvel_card INT(1)    COMMENT '是否要行程卡' ,
    PRIMARY KEY (id)
)  COMMENT = '表单图片选择';

DROP TABLE IF EXISTS message;
CREATE TABLE message(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    relation_id VARCHAR(32) NOT NULL   COMMENT '关联id' ,
    relation_user_id VARCHAR(32)    COMMENT '关联的userId' ,
    title VARCHAR(255)    COMMENT '标题' ,
    content VARCHAR(255)    COMMENT '内容' ,
    type INT    COMMENT '消息类型枚举 0通知 1提醒 2异常' ,
    PRIMARY KEY (id)
)  COMMENT = '消息模板';

DROP TABLE IF EXISTS form_records;
CREATE TABLE form_records(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    form_id VARCHAR(32) NOT NULL   COMMENT '表单Id' ,
    organization_id VARCHAR(32) NOT NULL   COMMENT '组织Id' ,
    tuple_id VARCHAR(32) NOT NULL   COMMENT '元组id' ,
    user_id VARCHAR(32) NOT NULL   COMMENT '用户id' ,
    status INT   DEFAULT 0 COMMENT '完成情况 0未完成 1已完成 2有异常' ,
    label_combination JSON    COMMENT '交文本标签组合方式' ,
    label_combination_img JSON    COMMENT '交图片的标签组合' ,
    healthy_code_url VARCHAR(255)    COMMENT '健康码url' ,
    travel_card_url VARCHAR(255)    COMMENT '行程卡url' ,
    PRIMARY KEY (id)
)  COMMENT = '表单记录';

DROP TABLE IF EXISTS form_count;
CREATE TABLE form_count(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    form_id VARCHAR(32) NOT NULL   COMMENT '表单id' ,
    undone INT   DEFAULT 0 COMMENT '未完成的数量' ,
    done INT   DEFAULT 0 COMMENT '完成的数量' ,
    abnormal INT   DEFAULT 0 COMMENT '异常的数量' ,
    PRIMARY KEY (id)
)  COMMENT = '表单计数';

DROP TABLE IF EXISTS message_user;
CREATE TABLE message_user(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    user_id VARCHAR(32) NOT NULL   COMMENT '用户id' ,
    message_id VARCHAR(32) NOT NULL   COMMENT '消息id' ,
    status INT(1)   DEFAULT 0 COMMENT '未读已读' ,
    apply_handle INT   DEFAULT 2 COMMENT '审批处理' ,
    PRIMARY KEY (id)
)  COMMENT = '通知用户表';

DROP TABLE IF EXISTS organization_special;
CREATE TABLE organization_special(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL   COMMENT '创建时间' ,
    update_time DATETIME NOT NULL   COMMENT '更新时间' ,
    title VARCHAR(255)    COMMENT '群组名称' ,
    PRIMARY KEY (id)
)  COMMENT = '特殊群组';

DROP TABLE IF EXISTS form_count_tuple;
CREATE TABLE form_count_tuple(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    form_id VARCHAR(32) NOT NULL   COMMENT '表单Id' ,
    tuple_id VARCHAR(32) NOT NULL   COMMENT '元组id' ,
    undone INT   DEFAULT 0 COMMENT '没做的' ,
    done INT   DEFAULT 0 COMMENT '做了正常的' ,
    abnormal INT   DEFAULT 0 COMMENT '做了不正常的' ,
    PRIMARY KEY (id)
)  COMMENT = '表单元组统计';

DROP TABLE IF EXISTS form_label_img;
CREATE TABLE form_label_img(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    title VARCHAR(255)    COMMENT '标签名称不能重名' ,
    PRIMARY KEY (id)
)  COMMENT = '图片标签';

DROP TABLE IF EXISTS img_label;
CREATE TABLE img_label(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    img_titile VARCHAR(255)    COMMENT '标签标题' ,
    PRIMARY KEY (id)
)  COMMENT = '标签要求上传的图片';

DROP TABLE IF EXISTS tuple_temp;
CREATE TABLE tuple_temp(
    id VARCHAR(32) NOT NULL   COMMENT '' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    organization_id VARCHAR(32) NOT NULL   COMMENT '组织Id' ,
    create_user_id VARCHAR(32) NOT NULL   COMMENT '创建者Id' ,
    titile VARCHAR(255)    COMMENT '题目' ,
    num INT   DEFAULT 1 COMMENT '人数' ,
    PRIMARY KEY (id)
)  COMMENT = '暂时元组';

