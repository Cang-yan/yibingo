package com.yibingo.race.core.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.yibingo.race.common.exception.BaseException;
import com.yibingo.race.core.service.enums.FormRemindEnum;
import com.yibingo.race.core.service.enums.MessageEnum;
import com.yibingo.race.core.service.enums.MessageUserEnum;
import com.yibingo.race.dal.detailMapper.MessageDetailMapper;
import com.yibingo.race.dal.entity.*;
import com.yibingo.race.dal.filterMapper.FormFilterMapper;
import com.yibingo.race.dal.filterMapper.FormRecordsFilterMapper;
import com.yibingo.race.dal.filterMapper.MessageFilterMapper;
import com.yibingo.race.dal.filterMapper.MessageUserFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 消息模板扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Service
public class MessageExtendsService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;
    @Autowired
    private MessageExtendsService messageExtendsService;
    @Autowired
    private MessageUserService messageUserService;
    @Autowired
    private MessageUserExtendsService messageUserExtendsService;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private FormService formService;
    @Autowired
    private FormExtendsService formExtendsService;
    @Autowired
    private FormRecordsExtendsService formRecordsExtendsService;

    /*
     * 申请入群,逻辑上应该是，创建元组/选择元组，然后入群，除了元组邀请的
     * 创建入群通知
     * */
    public void createApplyMessage(String organizationId, String userId, String nameInTuple, String tupleId) {
        Organization organization = organizationService.getById(organizationId);
        String content = "申请加入" + organization.getTitle();

        createMessage(organizationId, userId, MessageEnum.APPLY, organization.getCreateUserId(), nameInTuple, content);

    }


    /*
     * 创建异常通知
     * xx表
     * 元组名+出了异常情况
     * */
    public void createWarnMessage(String formId, String tupleName) {
        Form form = formService.getById(formId);
        String content = tupleName + "  出了异常情况，请排查";
        createMessage(formId, MessageEnum.WARN, form.getCreateUserId(), form.getTitle(), content);

    }


    /*
     * 创建提醒
     *  一般是批量创建
     *  */
    public void createBatchRemindMessage() {
        LocalDateTime currentLocalDateTime = LocalDateTimeUtil.now();

        FormFilterMapper formFilterMapper = new FormFilterMapper();
        //大于0
        formFilterMapper.scheduleRemindFrom = 0;
        List<Form> formList = formExtendsService.getListByFilter(formFilterMapper);
        List<Form> formToUpdate = new ArrayList<>();
        for (Form form : formList) {
            Long timeDifference = LocalDateTimeUtil.between(currentLocalDateTime, LocalDateTimeUtil.of(form.getEndTime()), ChronoUnit.MILLIS);

            //判断是哪个类型的通知，并计算相应的时间差
            if (form.getAttentionMode().equals(FormRemindEnum.FIVE_MINUTE.getValue())) {
                if (timeDifference.compareTo(FormRemindEnum.FIVE_MINUTE.getMs_num()) <= 0) {
                    createRemindMessage(form.getId());
                }
            } else if (form.getAttentionMode().equals(FormRemindEnum.TEN_MINUTE.getValue())) {
                if (timeDifference.compareTo(FormRemindEnum.TEN_MINUTE.getMs_num()) <= 0) {
                    createRemindMessage(form.getId());
                }
            } else if (form.getAttentionMode().equals(FormRemindEnum.FIFTEEN_MINUTE.getValue())) {
                if (timeDifference.compareTo(FormRemindEnum.FIFTEEN_MINUTE.getMs_num()) <= 0) {
                    createRemindMessage(form.getId());
                }
            }

            form.setScheduleRemind(form.getScheduleRemind() - 1);
            formToUpdate.add(form);
        }
        if (!formToUpdate.isEmpty()) {
            formService.updateBatchById(formToUpdate);
        }

    }

    public void createRemindMessage(String formId) {
        Message message = new Message();
        message.setRelationId(formId);
        message.setType(MessageEnum.REMIND.getStatus());
        message.setTitle("统计结束提醒！");
        message.setContent("您有统计表没有填写，请尽快！");
        messageService.save(message);
        FormRecordsFilterMapper formRecordsFilterMapper = new FormRecordsFilterMapper();
        formRecordsFilterMapper.formId = formId;
        formRecordsFilterMapper.status = 0;
        List<FormRecords> formRecordsList = formRecordsExtendsService.getListByFilter(formRecordsFilterMapper);
        List<String> userIdList = formRecordsList.stream().map(FormRecords::getUserId).collect(Collectors.toList());
        List<MessageUser> messageUserList = new ArrayList<>();
        for (String userId : userIdList) {
            MessageUser messageUser = new MessageUser();
            messageUser.setMessageId(message.getId());
            messageUser.setUserId(userId);
            messageUserList.add(messageUser);
        }
        if (!messageUserList.isEmpty()){
        messageUserService.saveBatch(messageUserList);

        }

    }


    /*
     * 创建通知
     * */
    public void createMessage(String relationId, MessageEnum messageEnum, String targetUserId, String title, String content) {
        Message message = new Message();
        //组织的元组中的姓名
        message.setTitle(title);
        message.setContent(content);
        message.setType(messageEnum.getStatus());
        message.setRelationId(relationId);

        messageService.save(message);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(targetUserId);
        messageUser.setMessageId(message.getId());
        messageUserService.save(messageUser);

    }

    /*
     * 创建申请通知  这个relationId应该是群组，其他类型的应该是表单id
     * */
    public void createMessage(String relationId, String relationUserId, MessageEnum messageEnum, String targetUserId, String title, String content) {
        Message message = new Message();
        //组织的元组中的姓名
        message.setTitle(title);
        message.setContent(content);
        message.setType(messageEnum.getStatus());
        message.setRelationId(relationId);
        message.setRelationUserId(relationUserId);

        messageService.save(message);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(targetUserId);
        messageUser.setMessageId(message.getId());
        messageUserService.save(messageUser);

    }


    /*
     * 查询未读消息数 建议打开页面就调
     * */
    public Map<String, Object> getUnreadMessage(String userId) {
        Integer apply = 0;
        Integer remind = 0;
        Integer warn = 0;
        MessageUserFilterMapper messageUserFilterMapper = new MessageUserFilterMapper();
        messageUserFilterMapper.userId = userId;
        messageUserFilterMapper.status = 0;
        List<MessageUser> messageUserList = messageUserExtendsService.getListByFilter(messageUserFilterMapper);
        if (!messageUserList.isEmpty()) {
            List<String> messageId = messageUserList.stream().map(MessageUser::getMessageId).collect(Collectors.toList());
            List<Message> messageList = messageService.listByIds(messageId);

            for (Message message : messageList) {
                if (message.getType().equals(MessageEnum.APPLY.getStatus())) apply++;
                if (message.getType().equals(MessageEnum.REMIND.getStatus())) remind++;
                if (message.getType().equals(MessageEnum.WARN.getStatus())) warn++;
            }

        }
        Map<String, Object> unreadMessage = new HashMap<>();
        unreadMessage.put("apply", apply);
        unreadMessage.put("remind", remind);
        unreadMessage.put("warn", warn);
        return unreadMessage;

    }

    /*
     * 查询消息,在controller里区分
     * */
    public List<Map<String, Object>> getMessage(MessageEnum messageEnum, String userId) {
        MessageUserFilterMapper messageUserFilterMapper = new MessageUserFilterMapper();
        messageUserFilterMapper.userId = userId;
        List<MessageUser> messageUserList = messageUserExtendsService.getListByFilter(messageUserFilterMapper);
        if (messageUserList.isEmpty()) return new ArrayList<>();
        MessageFilterMapper messageFilterMapper = new MessageFilterMapper();
        messageFilterMapper.type = messageEnum.getStatus();
        List<Message> messageList = messageExtendsService.getListByFilter(messageFilterMapper);
        List<MessageUser> toSaveMessageUser = new ArrayList<>();
        List<Map<String, Object>> endMap = new ArrayList<>();
        for (MessageUser messageUser : messageUserList){
            String head = "";

            Message message = messageList.stream().filter(
                    item -> messageUser.getMessageId() !=null && messageUser.getMessageId().equals(item.getId())
            ).findFirst().orElse(null);
            //如果为空  意味着不是本类型的信息
            if (message ==null) continue;

            Map<String, Object> messageMap = MessageDetailMapper.buildMap(message);
            if (messageEnum.equals(MessageEnum.APPLY)) {
                User user = userService.getById(message.getRelationUserId());
                if (user != null) head = user.getHead();

            } else {
                Form form = formService.getById(message.getRelationId());
                Organization organization = organizationService.getById(form.getOrganizationId());
                head = organization.getHead();
            }

            messageMap.put("head", head);
            messageMap.put("status", messageUser.getStatus());

            messageMap.put("applyStatus", messageUser.getApplyHandle());
            messageUser.setStatus(MessageUserEnum.READ.getStatus());
            toSaveMessageUser.add(messageUser);
            endMap.add(messageMap);


        }

        /*for (Message message : messageList) {
            String head = "";
            Map<String, Object> messageMap = MessageDetailMapper.buildMap(message);
            MessageUser messageUser = messageUserList.stream().filter(
                    item -> message.getId() != null && message.getId().equals(item.getMessageId())
            ).findFirst().orElse(null);
            if (messageUser == null) break;
            if (messageEnum.equals(MessageEnum.APPLY)) {
                User user = userService.getById(message.getRelationUserId());
                if (user != null) head = user.getHead();

            } else {
                Form form = formService.getById(message.getRelationId());
                Organization organization = organizationService.getById(form.getOrganizationId());
                head = organization.getHead();
            }

            messageMap.put("head", head);
            messageMap.put("status", messageUser.getStatus());

            messageMap.put("applyStatus", messageUser.getApplyHandle());
            messageUser.setStatus(MessageUserEnum.READ.getStatus());
            toSaveMessageUser.add(messageUser);
            endMap.add(messageMap);
        }*/
        messageUserService.updateBatchById(toSaveMessageUser);
        return endMap;

    }

    public List<Message> getListByFilter(MessageFilterMapper filterMapper) {

        QueryWrapper<Message> wrapper = new QueryWrapper<>();

        if (filterMapper.orderBy != null) {
            for (String orderBy : filterMapper.orderBy) {
                int desc = orderBy.indexOf("desc");
                int asc = orderBy.indexOf("asc");
                if (desc != -1 && asc == -1) {
                    wrapper.orderByDesc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, desc - 1)));
                }
                if (desc == -1 && asc != -1) {
                    wrapper.orderByAsc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, asc - 1)));
                }
            }
        } else {
            wrapper.orderByDesc("create_time");
        }

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time", new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time", new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time", new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time", new Date(filterMapper.createTimeTo));
        if (filterMapper.type != null) wrapper.eq("type", filterMapper.type);

        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<Message> markPage = new Page<>(page, row);

        Page<Message> resultList = messageService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}