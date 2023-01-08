package com.demo.merchant.web.controller;

import com.demo.merchant.client.service.*;
import com.demo.merchant.client.util.CommonUtils;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.MerchantQo;
import com.demo.merchant.object.ModelQo;
import com.demo.merchant.object.RoleQo;
import com.demo.merchant.object.UserQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRestService userService;
    @Autowired
    private RoleRestService roleService;
    @Autowired
    private MerchantRestService merchantRestService;

    @Autowired
    private UserFuture userFuture;
    @Autowired
    private RoleFuture roleFuture;

    @Value("${securityconfig.ssohome}")
    private String ssohome;


    @RequestMapping("/index")
    public CompletableFuture<String> index(ModelMap model, Principal user, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            List<ModelQo> menus = super.getModels(user.getName(), request);
            model.addAttribute("menus", menus);
            model.addAttribute("user", user);
            model.addAttribute("ssohome", ssohome);
            return "user/index";
        });
    }

    @RequestMapping(value="/{id}")
    public CompletableFuture<String> show(ModelMap model, @PathVariable Long id) {
        return userFuture.findById(id).thenApply(json -> {
            UserQo user = new Gson().fromJson(json, UserQo.class);
            model.addAttribute("user", user);
            return "user/show";
        });
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public CompletableFuture<Page<Map<String, Object>>> findAll(UserQo userQo, Principal user) {//, Principal user
        MerchantQo merchant = super.getMerchantByUserName(user.getName());
        userQo.setMerchant(merchant);
        return userFuture.findPage(userQo).thenApply( json -> {
            Gson gson = TreeMapConvert.getGson();
            TreeMap<String,Object> page = gson.fromJson(json, new TypeToken< TreeMap<String,Object>>(){}.getType());

            Pageable pageable = new PageRequest(userQo.getPage(), userQo.getSize(), null);
            List<UserQo> list = new ArrayList<>();

            if(page != null && page.get("content") != null)
                list = gson.fromJson(page.get("content").toString(), new TypeToken<List<UserQo>>(){}.getType());
            String count = page.get("totalelements").toString();

            return new PageImpl(list, pageable, new Long(count));
        });
    }

    @RequestMapping("/new")
    public CompletableFuture<String> create(ModelMap model, UserQo user, HttpServletRequest request){
        return roleFuture.findList().thenApply( json -> {
            List<RoleQo> roles = new Gson().fromJson(json, new TypeToken<List<RoleQo>>() {}.getType());
            request.getSession().setAttribute("roles", roles);
            model.addAttribute("roles", roles);
            model.addAttribute("user", user);
            return "user/new";
        });
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public CompletableFuture<String> save(UserQo userQo, Principal principal, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            String json = userService.findByName(principal.getName());
            UserQo loginUser = new Gson().fromJson(json, UserQo.class);

            userQo.setMerchant(loginUser.getMerchant());
            BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
            userQo.setPassword(bpe.encode(userQo.getPassword()));

            String[] rls = request.getParameterValues("rls");
            List<RoleQo> roleQos = (List<RoleQo>) request.getSession().getAttribute("roles");
            if(rls != null && rls.length > 0) {
                for (String rl : rls) {
                    for (RoleQo roleQo : roleQos) {
                        if (roleQo.getId().compareTo(new Long(rl)) == 0) {
                            userQo.addRole(roleQo);
                        }
                    }
                }
            }

            String sid = userService.create(userQo);
            logger.info("新增->ID=" + sid);

            return "1";
        });
    }

    @RequestMapping(value="/edit/{id}")
    public CompletableFuture<String> edit(ModelMap model, @PathVariable Long id, HttpServletRequest request){
        return CompletableFuture.supplyAsync(() -> {
            String json = userService.findById(id);
            UserQo userQo = new Gson().fromJson(json, UserQo.class);

            String rolestr = roleService.findList();
            List<RoleQo> roleQos = new Gson().fromJson(rolestr, new TypeToken<List<RoleQo>>() {}.getType());
            request.getSession().setAttribute("roles", roleQos);

            List<Long> rids = new ArrayList<>();
            for (RoleQo role : userQo.getRoles()) {
                rids.add(role.getId());
            }

            model.addAttribute("user", userQo);
            model.addAttribute("roles", roleQos);
            model.addAttribute("rids", rids);
            return "user/edit";
        });
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public CompletableFuture<String> update(UserQo userQo, HttpServletRequest request) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            String merchantid = request.getParameter("merchantid");
            if(CommonUtils.isNotNull(merchantid)) {
                String json = merchantRestService.findById(new Long(merchantid));
                MerchantQo merchantQo = new Gson().fromJson(json, MerchantQo.class);
                if(merchantQo != null)
                    userQo.setMerchant(merchantQo);
            }

            String[] rls = request.getParameterValues("rls");
            List<RoleQo> roleQos = (List<RoleQo>) request.getSession().getAttribute("roles");
            if(rls != null && rls.length > 0) {
                for (String rl : rls) {
                    for (RoleQo roleQo : roleQos) {
                        if (roleQo.getId().compareTo(new Long(rl)) == 0) {
                            userQo.addRole(roleQo);
                        }
                    }
                }
            }

            String sid = userService.update(userQo);

            logger.info("修改->ID=" + sid);
            return "1";
        });
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CompletableFuture<String> delete(@PathVariable Long id) throws Exception{
        return userFuture.delete(id).thenApply( sid -> {
            logger.info("删除->ID="+sid);
            return "1";
        });
    }

}
