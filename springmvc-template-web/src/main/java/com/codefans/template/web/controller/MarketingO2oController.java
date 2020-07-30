//package com.codefans.template.web.controller;/**
// * Created by codefans on 2017/6/26.
// */
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.google.common.base.Throwables;
//import com.le.jr.marketing.restapi.common.constants.RestApiConst;
//import com.le.jr.marketing.restapi.common.enums.CodeEnum;
//import com.le.jr.marketing.restapi.common.utils.ValidateUtil;
//import com.le.jr.marketing.restapi.service.MarketingO2oService;
//import com.le.jr.marketing.restapi.vo.UserTokenReturnVo;
//import com.le.jr.marketing.restapi.web.client.RestClient;
//import com.le.jr.trade.publictools.Messages;
//import com.le.jr.trade.publictools.data.Code;
//import com.le.jr.trade.publictools.data.Message;
//import com.le.jr.trade.publictools.data.Result;
//import com.le.jr.trade.publictools.exception.BizException;
//import com.le.jr.trade.publictools.util.JsonUtils;
//import com.le.jr.trade.shorturl.domain.dto.ShortUrlDto;
//import com.lejr.marketing.o2o.util.RSAUtil;
//import com.lejr.platform.common.page.PageVo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author: codefans
// * @date: 2017-06-26 19:40
// **/
//@Controller
//@RequestMapping("promotion/marketingO2o")
//public class MarketingO2oController {
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CardCouponsController.class);
//
//    @Resource
//    private MarketingO2oService marketingO2oService;
//
//    /**
//     * @Description: 店员和客户关系绑定
//     * @author codefans
//     * @param
//     * @date 2017-06-29 16:55
//     */
//    @RequestMapping(method= RequestMethod.POST, value="/crmRelationshipBinding/{version}/{appkey}")
//    @ResponseBody
//    public Message<ClerkCustomerBindingDto> crmRelationshipBinding(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @RequestParam(value = "paraString") String paraString) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,paraString)) {
//                LOGGER.error("参数校验失败version={},appkey={},paraString={}", version, appkey, paraString);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//
//            LOGGER.info("paraString={}", paraString);
//
//            ClerkCustomerBindParamDto bindParamDto = JsonUtils.readValue(paraString, ClerkCustomerBindParamDto.class);
//
//            String encodeLeId = bindParamDto.getLeId();
//            String encodeLeShopId = bindParamDto.getLeShopId();
//            String displayCode = bindParamDto.getDisplayCode();
//
//            String decodeLeId = RSAUtil.decryptByPrivateKey(encodeLeId);
//            String decodeLeShopId = RSAUtil.decryptByPrivateKey(encodeLeShopId);
//
//            String token = bindParamDto.getCustomToken();
//
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//
//            //过滤店员和客户是同一个人的数据
//            String letvUserIdStr = String.valueOf(letvUserId);
//            if(StringUtils.isNotEmpty(letvUserIdStr) && letvUserIdStr.equals(decodeLeId)) {
//                return Messages.failed(Code.FAIL.getValue(), "店员和客户不能是同一个人.");
//            }
//
//            Message<ClerkCustomerBindingDto> message = marketingO2oService.crmRelationshipBinding(letvUserId, decodeLeId, decodeLeShopId, displayCode);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                if(message == null) {
//                    return Messages.failed(Result.FAILED.getValue(), "绑定关系失败");
//                } else {
//                    return Messages.failed(message.getCode(), message.getMessage());
//                }
//            }
//
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("绑定关系异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "绑定关系异常");
//        }
//    }
//
//    /**
//     * @Description: 添加店员
//     * @author codefans
//     * @param
//     * @date 2017-05-11 09:05
//     */
//    @RequestMapping("/addClerk/{version}/{appkey}/{token}/{name}/{telePhone}/{noID}/{leparID}")
//    @ResponseBody
//    public Message<StatusResultDto> addClerk(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "name") String name,
//            @PathVariable(value = "telePhone") String telePhone,
//            @PathVariable(value = "noID") String noID,
//            @PathVariable(value = "leparID") String leparID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,token,name,telePhone, noID, leparID)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={},name={},telePhone={}, noID={}, leparID={}", version, appkey, token, name, telePhone, noID, leparID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<StatusResultDto> message = marketingO2oService.addClerk(letvUserId, name, telePhone, noID, leparID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(message.getCode(), message.getMessage());
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("添加店员异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "添加店员异常");
//        }
//    }
//
//
//    /**
//     * @Description: 获取首页业绩信息详情
//     * @author codefans
//     * @param
//     * @date 2017-05-02 14:09
//     */
//    @RequestMapping("/indexCommission/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<IndexCommissionDto> queryIndexCommissionList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,token)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}", version, appkey, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<IndexCommissionDto> message = marketingO2oService.queryIndexCommissionList(letvUserId);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取首页业绩信息列表失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取首页业绩信息详情异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取首页业绩信息详情异常");
//        }
//    }
//
//    /**
//     * @Description: 获取首页理财列表
//     * @author codefans
//     * @param
//     * @date 2017-05-15 09:46
//     */
//    @RequestMapping("/products/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<FinancialProductResultDto> queryIndexFinancialProductList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token) {
//
//        String methodLogTips = "获取首页理财列表";
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,token)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}", version, appkey, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<FinancialProductResultDto> message = marketingO2oService.queryIndexFinancialProductList(letvUserId);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), methodLogTips + "失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info(methodLogTips + "异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), methodLogTips + "异常");
//        }
//    }
//
//    /**
//     * @Description: 获取个人业绩列表
//     * @author codefans
//     * @param
//     * @date 2017-05-04 09:43
//     */
//    @RequestMapping("/personalPerformance/{version}/{appkey}/{token}/{month}/{pageSize}/{currentPageNo}")
//    @ResponseBody
//    public Message<ClerkPerformanceResultDto> queryPersonalPerformanceList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "month") String month,
//            @PathVariable(value = "pageSize") Integer pageSize,
//            @PathVariable(value = "currentPageNo") Integer currentPageNo) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,token,currentPageNo)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={},currentPageNo={}", version, appkey, token, currentPageNo);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<ClerkPerformanceResultDto> message = marketingO2oService.queryPersonalPerformanceList(letvUserId, month, pageSize, currentPageNo);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取个人业绩信息列表失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取个人业绩信息列表异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取个人业绩信息列表异常");
//        }
//    }
//
//
//    /**
//     * @Description: 获取团队业绩列表
//     * @author codefans
//     * @param
//     * @date 2017-05-04 21:08
//     */
//    @RequestMapping("/teamPerformance/{version}/{appkey}/{token}/{type}/{month}/{pageSize}/{currentPageNo}")
//    @ResponseBody
//    public Message<LeParPerformanceResultDto> queryTeamPerformanceList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "type") Integer type,
//            @PathVariable(value = "month") String month,
//            @PathVariable(value = "pageSize") Integer pageSize,
//            @PathVariable(value = "currentPageNo") Integer currentPageNo) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,appkey,token,currentPageNo)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={},currentPageNo={}", version, appkey, token, currentPageNo);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<LeParPerformanceResultDto> message = marketingO2oService.queryTeamPerformanceList(letvUserId, type, month, pageSize, currentPageNo);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取团队业绩信息列表失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取团队业绩信息列表异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取团队业绩信息列表异常");
//        }
//    }
//
//
//
//
//    /**
//     * @Description: 获取客户详情列表
//     * @author codefans
//     * @param
//     * @date 2017-04-28 11:09
//     */
//    @RequestMapping("/customerDetails/{version}/{appKey}/{token}/{type}/{pageSize}/{currentPageNo}/{assistantID}/{storeID}")
//    @ResponseBody
//    public Message<CustomerDetailResultDto> queryCustomerDetailLists(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appKey") String appKey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "type") Integer type,
//            @PathVariable(value = "pageSize") Integer pageSize,
//            @PathVariable(value = "currentPageNo") Integer currentPageNo,
//            @PathVariable(value = "assistantID") String assistantID,
//            @PathVariable(value = "storeID") String storeID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,token)) {
//                LOGGER.error("参数校验失败version={},token={}", version, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<CustomerDetailResultDto> message = marketingO2oService.queryCustomerDetailList(letvUserId, type, pageSize, currentPageNo, assistantID, storeID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取客户详情列表失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取客户详情列表异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取客户详情列表异常");
//        }
//    }
//
//    /**
//     * @Description: 获取客户详情
//     * @author codefans
//     * @param
//     * @date 2017-05-20 19:23
//     */
//    @RequestMapping("/customer/{version}/{appkey}/{token}/{userID}")
//    @ResponseBody
//    public Message<CustomerDetailDto> queryCustomerDetail(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "userID") String userID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version,token, userID)) {
//                LOGGER.error("参数校验失败version={},token={}, userID={}", version, token, userID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<CustomerDetailDto> message = marketingO2oService.queryCustomerDetail(letvUserId, userID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取客户详情失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取客户详情异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取客户详情异常");
//        }
//    }
//
//
//    /**
//     * @Description: 获取个人详细信息
//     * @author codefans
//     * @param
//     * @date 2017-05-10 09:17
//     */
//    @RequestMapping("/clerkDetail/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<ClerkDetailInfoDto> queryClerkDetailInfo(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}", version, appkey, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<ClerkDetailInfoDto> message = marketingO2oService.queryClerkDetailInfo(letvUserId);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取店员详情失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取店员详情异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取店员详情异常");
//        }
//    }
//
//    /**
//     * @Description: 获取我的店铺
//     * @author codefans
//     * @param
//     * @date 2017-05-10 09:40
//     */
//    @RequestMapping("/stores/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<LeparInfoResultDto> queryMyStoreList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}", version, appkey, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<LeparInfoResultDto> message = marketingO2oService.queryMyStoreList(letvUserId);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取我的店铺失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取我的店铺异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取我的店铺异常");
//        }
//    }
//
//    /**
//     * @Description: 获取店长的某家店铺
//     * @author codefans
//     * @param
//     * @date 2017-05-20 21:10
//     */
//    @RequestMapping("/managerStore/{version}/{appkey}/{token}/{storeID}")
//    @ResponseBody
//    public Message<ManagerStoreDto> queryManagerStore(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "storeID") String storeID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token, storeID)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}, storeID={}", version, appkey, token, storeID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<ManagerStoreDto> message = marketingO2oService.queryManagerStore(letvUserId, storeID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取店长的某家店铺失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取店长的某家店铺.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取店长的某家店铺");
//        }
//    }
//
//
//    /**
//     * @Description: 获取店铺人员列表
//     * @author codefans
//     * @param
//     * @date 2017-05-10 09:40
//     */
//    @RequestMapping("/clerks/{version}/{appkey}/{token}/{leparID}/{currentPageNo}/{pageSize}")
//    @ResponseBody
//    public Message<LeparStaffResultDto> queryStoreClerkList(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "leparID") String leparID,
//            @PathVariable(value = "currentPageNo") Integer currentPageNo,
//            @PathVariable(value = "pageSize") Integer pageSize) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}", version, appkey, token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<LeparStaffResultDto> message = marketingO2oService.queryStoreClerkList(letvUserId, leparID, currentPageNo, pageSize);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "获取店铺人员列表失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("获取店铺人员列表异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取店铺人员列表异常");
//        }
//    }
//
//    /**
//     * @Description: 移除店员
//     * @author codefans
//     * @param
//     * @date 2017-05-10 09:55
//     */
//    @RequestMapping("/removeClerk/{version}/{appkey}/{token}/{leparID}/{userID}")
//    @ResponseBody
//    public Message<RemoveClerkDto> removeClerk(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "leparID") String leparID,
//            @PathVariable(value = "userID") String userID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token, leparID)) {
//                LOGGER.error("参数校验失败version={}, appkey={}, token={}, leparID={}", version, appkey, token, leparID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<RemoveClerkDto> message = marketingO2oService.removeClerk(letvUserId, leparID, userID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "移除店员失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("移除店员异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "移除店员异常");
//        }
//    }
//
//    /**
//     * @Description: 修改店员所属门店
//     * @author codefans
//     * @param
//     * @date 2017-05-10 13:12
//     */
//    @RequestMapping("/modifyClerkStore/{version}/{appkey}/{token}/{userID}/{aimLeparID}/{leparID}")
//    @ResponseBody
//    public Message<ModifyClerkStoreDto> modifyClerkStore(
//            @PathVariable(value = "version") String version,
//            @PathVariable(value = "appkey") String appkey,
//            @PathVariable(value = "token") String token,
//            @PathVariable(value = "userID") String userID,
//            @PathVariable(value = "aimLeparID") String aimLeparID,
//            @PathVariable(value = "leparID") String leparID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(version, appkey, token, aimLeparID, leparID)) {
//                LOGGER.error("参数校验失败version={},appkey={},token={}, aimLeparID={}, leparID={}", version, appkey, token, aimLeparID, leparID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Long letvUserId = Long.parseLong(userId);
//
//            Message<ModifyClerkStoreDto> message = marketingO2oService.modifyClerkStore(letvUserId, leparID, userID, aimLeparID);
//            if(message == null || !Result.isSuccess(message.getResult())) {
//                return Messages.failed(Code.FAIL.getValue(), "修改店员所属门店失败");
//            }
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("修改店员所属门店异常.异常信息：{}", Throwables.getStackTraceAsString(e));
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "修改店员所属门店异常");
//        }
//    }
//
//    /**
//     * 获取所有客户选择移动到的门店的列表
//     *
//     * @param version
//     * @param appkey
//     * @param token
//     * @return
//     */
//    @RequestMapping("/queryOwnStore/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<List<OwnStoreDto>> queryOwnStore(@PathVariable(value = "version") String version,
//                                                    @PathVariable(value = "appkey") String appkey, @PathVariable(value = "token") String token) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token)) {
//                LOGGER.error("参数校验失败token={}", token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Message<List<OwnStoreDto>> result = marketingO2oService.queryOwnStore(Long.valueOf(userId));
//
//            return result;
//        } catch (Exception e) {
//            LOGGER.info("获取所有客户选择移动到的门店的列表异常.异常信息：{}", e);
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取所有客户选择移动到的门店的列表异常");
//        }
//    }
//
//    /**
//     * 将客户批量移动至选择的门店和店员下面
//     *
//     * @param version
//     * @param appkey
//     * @param token
//     * @param storeID 门店ID
//     * @param assistantID 店员ID
//     * @param userID 用户ID，批量移动无主客户时用|分割，如id1|id2，
//    当此字段不为空时，移动特定用户至指定店员名下
//    当此字段为空时，移动指定门店下所有无主客户至指定店员名下
//     * @return
//     */
//    @RequestMapping("/moveCustomer/{version}/{appkey}/{token}/{storeID}/{assistantID}/{userID}")
//    @ResponseBody
//    public Message<Map<String, Integer>> moveCustomer(@PathVariable(value = "version") String version,
//                                                      @PathVariable(value = "appkey") String appkey, @PathVariable(value = "token") String token,
//                                                      @PathVariable(value = "storeID") String storeID, @PathVariable(value = "assistantID") String assistantID,
//                                                      @PathVariable(value = "userID") String userID) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token, storeID, assistantID)) {
//                LOGGER.error("参数校验失败token={},storeID={},assistantID={}", token,storeID,assistantID);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String letvuserId = returnVo.getBean().getResult();
//            Message<Map<String, Integer>> result = marketingO2oService.moveCustomer(Long.valueOf(letvuserId), storeID, assistantID, userID);
//
//            return result;
//        } catch (Exception e) {
//            LOGGER.info("获取所有客户选择移动到的门店的列表异常.异常信息：{}", e);
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取所有客户选择移动到的门店的列表异常");
//        }
//    }
//
//    /**
//     * 查询店长管理门店
//     *
//     * @param version
//     * @param appkey
//     * @param token
//     * @return
//     */
//    @RequestMapping("/queryMyStore/{version}/{appkey}/{token}")
//    @ResponseBody
//    public Message<List<OwnStoreDto>> queryMyStore(@PathVariable(value = "version") String version,
//                                                   @PathVariable(value = "appkey") String appkey, @PathVariable(value = "token") String token) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token)) {
//                LOGGER.error("参数校验失败token={}", token);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String userId = returnVo.getBean().getResult();
//            Message<List<OwnStoreDto>> result = marketingO2oService.queryMyStore(Long.valueOf(userId));
//
//            return result;
//        } catch (Exception e) {
//            LOGGER.info("查询店长管理门店列表异常.异常信息：{}", e);
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "查询店长管理门店异常");
//        }
//    }
//
//    /**
//     * 查询商城订单
//     *
//     * @param token
//     * @param pageNo
//     * @param pageSize
//     * @return
//     */
//    @RequestMapping("/getLetvOrderList/{token}/{pageNo}/{pageSize}")
//    @ResponseBody
//    public Message<PageVo<MarketingLetvoders>> getOrderList(@PathVariable(value = "token") String token,
//                                                            @PathVariable(value = "pageNo") Integer pageNo, @PathVariable(value = "pageSize") Integer pageSize) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token, pageNo, pageSize)) {
//                LOGGER.error("参数校验失败token={},pageNo={},pageSize={}", token, pageNo, pageSize);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            Message<PageVo<MarketingLetvoders>> message = marketingO2oService.getOrderList(pageNo, pageSize, null, null);
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("getOrderList.异常信息：{}", e.getLocalizedMessage());
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "查询商城订单异常");
//        }
//    }
//
//    /**
//     * 获取外呼请求地址
//     *
//     * @param token
//     * @param orderId
//     * @return
//     */
//    @RequestMapping("/getCallContent/{token}/{orderId}")
//    @ResponseBody
//    public Message<String> getCallContent(@PathVariable(value = "token") String token, @PathVariable(value = "orderId") String orderId) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token, orderId)) {
//                LOGGER.error("参数校验失败token={},orderId={}", token, orderId);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            String message = marketingO2oService.getCallContent(orderId);
//            if(message == null){
//                return Messages.failed(0, "获取订单信息失败");
//            }
//            return Messages.success(message);
//        } catch (Exception e) {
//            LOGGER.info("getCallContent.异常信息：{}", e.getLocalizedMessage());
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "获取外呼请求地址异常");
//        }
//
//    }
//
//    @RequestMapping("/updateCall/{token}/{orderId}")
//    @ResponseBody
//    public Message<Boolean> updateCall(@PathVariable(value = "token") String token, @PathVariable(value = "orderId") String orderId) {
//        try {
//            if (!ValidateUtil.isNotEmpty(token, orderId)) {
//                LOGGER.error("参数校验失败token={},orderId={}", token, orderId);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            Message<Boolean> message = marketingO2oService.updateCall(orderId);
//
//            return message;
//        } catch (Exception e) {
//            LOGGER.info("updateCall.异常信息：{}", e.getLocalizedMessage());
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "更新订单已外呼异常");
//        }
//
//    }
//
//    @RequestMapping(value = "/shorturl/add/v1/{appKey}", method = RequestMethod.POST)
//    @ResponseBody
//    public Message<Map<String, String>> addShortUrl(HttpServletRequest request) {
//        try {
//            String token = request.getParameter("token");
//            String pageName = request.getParameter("pageName");
//            String type = request.getParameter("type");
//            String url = request.getParameter("url");
//
//            if (!ValidateUtil.isNotEmpty(token, pageName, type, url)) {
//                LOGGER.error("参数校验失败token={},pageName={},type={},url={}", token, pageName, type, url);
//                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
//            }
//
//            // 解析token
//            String resultJsonStr = RestClient.getGetRequestResult(RestApiConst.CHECK_TOKEN_VALIDITY, token, String.class);
//            LOGGER.info(resultJsonStr);
//            UserTokenReturnVo returnVo = JsonUtils.readValue(resultJsonStr, UserTokenReturnVo.class);
//            if (Result.FAILED.getValue() == returnVo.getStatus() || returnVo.getBean() == null) {
//                return Messages.failed(CodeEnum.TOKEN_VALIDATE_FAILED.getValue(), "用户token验证失败");
//            }
//            Long letvUserId = Long.parseLong(returnVo.getBean().getResult());
//
//            ShortUrlDto shortUrlDto = new ShortUrlDto();
//            shortUrlDto.setPageName(pageName);
//            shortUrlDto.setType(Byte.valueOf(type));
//            shortUrlDto.setUrl(url);
//            shortUrlDto.setCreateUser(letvUserId + "");
//            String shortUrl = marketingO2oService.generateShortUrl(shortUrlDto);
//            Map<String, String> retMap = new HashMap<>();
//            retMap.put("shortUrl", shortUrl);
//            return Messages.success(retMap);
//        } catch (BizException e) {
//            return Messages.failed(e.getCode(), e.getMessage());
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            return Messages.failed(Code.SYSTEMEXCEPTION.getValue());
//        }
//    }
//
//
//}
