package com.company.microserviceuser.controller;

import com.company.microserviceuser.exception.MyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.enums.MyCodeEnums;
import com.company.microserviceuser.service.*;
import com.company.microserviceuser.vo.common.*;
import com.company.microserviceuser.vo.*;
import com.company.microserviceuser.dos.*;
import com.company.microserviceuser.utils.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.company.microserviceuser.constant.StringConstant.DATE_Y_M_D_H_M_S;
import com.company.microserviceuser.constant.*;
/**
 * UserController API.
 * @author xindaqi 
 * @since 2020-10-26
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "人员信息")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired 
    private TimeProcessUtil timeProcessUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoderCrypt;

    @PostMapping(value = "/save")
    @ApiOperation(value = "注册用户")
    @ApiImplicitParam(name = "params", value = "用户注册信息", dataType = "RegisterInputDTO", paramType = "body")
    public ResponseVO<String> save(@RequestBody RegisterInputDTO params) {
        String passwordRaw = params.getPassword();
        String passwordEncode = passwordEncoderCrypt.encode(passwordRaw);
        String createdTime = timeProcessUtil.timeGenerateFromPattern(DATE_Y_M_D_H_M_S);
        UserDO userDO = new UserDO();
        userDO.setUsername(params.getUsername());
        userDO.setSex(params.getSex());
        userDO.setAddress(params.getAddress());
        userDO.setPassword(passwordEncode);
        userDO.setPhoneNumber(params.getPassword());
        userDO.setCreatedTime(createdTime);
        Integer saveFlag = userService.registerUser(userDO);
        if(saveFlag == 1) {
            logger.info("成功--注册用户");
            return new ResponseVO<String>().ok(passwordEncode);
        }else {
            logger.info("失败--注册用户");
            return new ResponseVO<String>().fail();
        }

    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "params", value = "用户信息", dataType = "LoginInputDTO", paramType = "body")
    public ResponseVO<String> login(@RequestBody @Valid LoginInputDTO params) throws MyException {
        
        LoginOutputDTO loginOutputDTO = userService.login(params);
        if(null == loginOutputDTO) {
            throw new MyException(MyCodeEnums.USERNAME_PASSWORD_ERROR.getCode(), MyCodeEnums.USERNAME_PASSWORD_ERROR.getMsg());
        }
        String passwordInDatabase = loginOutputDTO.getPassword();
        String passwordFromUser = params.getPassword();
        Boolean passwordCmp = passwordEncoderCrypt.matches(passwordFromUser, passwordInDatabase);
        if(Boolean.TRUE.equals(passwordCmp)) {
            String token = JwtUtil.generateToken(params.getUsername());
            return new ResponseVO<String>().ok(token);
        }
        return new ResponseVO<String>().invalid(MyCodeEnums.USERNAME_PASSWORD_ERROR.getMsg());

    }

    @DeleteMapping(value = "/delete/physical")
    @ApiOperation("物理删除用户")
    @ApiImplicitParam(name = "id", value = "用户信息", dataType = "Long", paramType = "query")
    public ResponseVO<String> deleteUserPhysical(@RequestParam Long id) {
        try{
            Integer deleteFlag = userService.deleteUserPhysical(id);
            if(deleteFlag == 1) {
                logger.info("成功--物理删除用户");
                return new ResponseVO<String>().ok("成功删除");
            }else {
                logger.warn("失败--物理删除用户");
                return new ResponseVO<String>().fail();
            }
        }catch (Exception e) {
            logger.error("异常--物理删除用户：", e);
            return new ResponseVO<String>().fail();
        }
        
    }

    @DeleteMapping(value = "/delete/logical")
    @ApiOperation("逻辑删除用户")
    @ApiImplicitParam(name = "id", value = "用户信息", dataType = "Long", paramType = "query")
    public ResponseVO<String> deleteUserLogical(@RequestParam Long id) {
        try{
            String updatedTime = timeProcessUtil.timeGenerateFromPattern("yyyy-MM-dd HH:mm:ss");
            UserDO userDo = new UserDO();
            userDo.setUpdatedTime(updatedTime);
            userDo.setDelete(1);
            userDo.setId(id);
            Integer deleteFlag = userService.deleteUserLogical(userDo);
            if(deleteFlag == 1) {
                logger.info("成功--逻辑删除用户");
                return new ResponseVO<String>().ok("成功删除");
            }else {
                logger.error("失败--逻辑删除用户");
                return new ResponseVO<String>().fail();
            }
        }catch (Exception e) {
            logger.error("异常--逻辑删除用户:", e);
            return new ResponseVO<String>().fail();
        }
        
    }

    @PostMapping(value = "/edit")
    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "params", value = "修改用户注册信息", dataType = "EditUserInputDTO", paramType = "body")
    public ResponseVO<String> editUser(@RequestBody EditUserInputDTO params) {
        try{
            String updatedTime = timeProcessUtil.timeGenerateFromPattern("yyyy-MM-dd HH:mm:ss");
            UserDO userDo = new UserDO();
            userDo.setUpdatedTime(updatedTime);
            BeanUtils.copyProperties(params, userDo);
            Integer editFlag = userService.editUser(userDo);
            if(editFlag == 1) {
                logger.info("成功--修改用户信息");
                return new ResponseVO<String>().ok("成功修改");
            }else {
                logger.error("失败--修改用户信息");
                return new ResponseVO<String>().fail();
            }
        }catch (Exception e) {
            logger.error("异常--修改用户信息: ", e);
            return new ResponseVO<String>().fail();
        }
        
    }

    @GetMapping(value = "/query")
    @ApiOperation("查询用户")
    @ApiImplicitParam(name = "id", value = "用户注册信息", dataType = "Integer", paramType = "query")
    public ResponseVO<UserDetailsVO> query(@RequestParam Long id) {
        try{
            UserDetailsVO userInformation = userService.queryUserDetails(id);
            logger.info("成功--查询用户详情信息");
            return new ResponseVO<UserDetailsVO>().ok(userInformation);
            
        }catch (Exception e) {
            logger.error("失败--查询用户详情信息: ", e);
            return new ResponseVO<UserDetailsVO>().fail();
        }
    }

    @PostMapping(value = "/query/page")
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户v0.0", httpMethod = "POST")
    @ApiImplicitParam(name = "params", value = "分页查询用户注册信息", dataType = "QueryUserByPageInputDTO", paramType = "body")
    public ResponseVO<List<UserDetailsVO>> queryUserByPage(@RequestBody QueryUserByPageInputDTO params) {
        try{
            UserDO userDo = new UserDO();
            Integer pageNum = params.getPageNum();
            Integer pageSize = params.getPageSize();
            BeanUtils.copyProperties(params, userDo);
            PageInfo<UserDetailsVO> userInformation = userService.queryUserByPage(userDo, pageNum, pageSize);
            List<UserDetailsVO> userList = userInformation.getList();
            Long total = userInformation.getTotal();
            logger.info("成功--分页查询用户");
            return new ResponseVO<List<UserDetailsVO>>().ok(userList, total);
            
        }catch (Exception e) {
            logger.error("失败--列表分页查询用户:", e);
            return new ResponseVO<List<UserDetailsVO>>().fail();
        }
        
    }

    @PostMapping(value = "/query/idlist")
    @ApiOperation("id列表分页查询用户")
    @ApiImplicitParam(name = "params", value = "分页查询用户注册信息", dataType = "QueryUserByIdListInputDTO", paramType = "body")
    public ResponseVO<List<UserDetailsVO>> queryUserByIdList(@RequestBody QueryUserByIdListInputDTO params) {
        try{
            PageInfo<UserDetailsVO> userInformation = userService.queryUserByIdList(params);
            List<UserDetailsVO> userList = userInformation.getList();
            Long total = userInformation.getTotal();
            logger.info("成功--id列表分页查询用户");
            return new ResponseVO<List<UserDetailsVO>>().ok(userList, total);
            
        }catch (Exception e) {
            logger.error("失败--id列表分页查询用户:", e);
            return new ResponseVO<List<UserDetailsVO>>().fail();
        }
        
    }

    @GetMapping(value = "/query/user/role")
    @ApiOperation("查询用户及角色")
    @ApiImplicitParam(name="id", value="用户id", dataType="Long", paramType="query")
    public ResponseVO<UserAndRoleVO> queryUserAndRole(@RequestParam("id") Long id) {
        try {
            UserAndRoleVO userAndRoleVO = userService.queryUserAndRole(id);
            logger.error("成功--查询用户及角色信息");
            return new ResponseVO<UserAndRoleVO>().ok(userAndRoleVO);
        }catch(Exception e) {
            logger.error("失败--查询用户及角色信息:", e);
            return new ResponseVO<UserAndRoleVO>().fail();

        }  
    }

    @PostMapping(value = "/custom/exception")
    @ApiOperation("自定义异常测试")
    @ApiImplicitParam(name = "params", value = "列表数据", dataType = "LoginInputDTO", paramType="body")
    public ResponseVO<LoginInputDTO> myException(@RequestBody LoginInputDTO params) throws MyException {
        if(null == params || null == params.getUsername() || null == params.getPassword()) {
            throw MyException.paramException("我是异常");
        }
        return new ResponseVO<LoginInputDTO>().ok(params);
    }

    @GetMapping(value = "/method/annotation")
    @ApiOperation("自定义方法注解测试")
    public String methodAnnotationTest() {
        return timeProcessUtil.timeGenerateFromPattern(StringConstant.DATE_Y_M_D_H_M_S);
    }

    @PostMapping(value = "/add/batch/test")
    @ApiOperation("批量注册用户")
    @ApiImplicitParam(name = "params", value = "用户注册信息", dataType = "List<UserDO>", paramType = "body")
    public ResponseVO<String> addUserBatch(@RequestBody List<UserDO> params) {

        Integer addUserBatchFlag = userService.registerUserBatch(params);
        logger.info("成功--批量添加用户");
        return new ResponseVO<String>().ok("成功批量添加用户");

    }
    
}