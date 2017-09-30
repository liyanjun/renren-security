package io.renren.controller;

import java.util.List;
import java.util.Map;

import io.renren.entity.SysUserEntity;
import io.renren.utils.ShiroUtils;
import io.renren.validator.ValidatorUtils;
import io.renren.validator.group.AddGroup;
import io.renren.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.GamePropertyEntity;
import io.renren.service.GamePropertyService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 属性表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
@RestController
@RequestMapping("gameproperty")
public class GamePropertyController {
    @Autowired
    private GamePropertyService gamePropertyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gameproperty:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GamePropertyEntity> gamePropertyList = gamePropertyService.queryList(query);
        int total = gamePropertyService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gamePropertyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gameproperty:info")
    public R info(@PathVariable("id") Long id) {
        GamePropertyEntity gameProperty = gamePropertyService.queryObject(id);

        return R.ok().put("gameProperty", gameProperty);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("gameproperty:save")
    public R save(@RequestBody GamePropertyEntity gameProperty) {
        ValidatorUtils.validateEntity(gameProperty, AddGroup.class);
        Long userId = ShiroUtils.getUserId();
        gameProperty.setCreatorId(userId);
        gamePropertyService.save(gameProperty);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("gameproperty:update")
    public R update(@RequestBody GamePropertyEntity gameProperty) {
        ValidatorUtils.validateEntity(gameProperty, UpdateGroup.class);
        Long userId = ShiroUtils.getUserId();
        gameProperty.setUpdateId(userId);
        gamePropertyService.update(gameProperty);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("gameproperty:delete")
    public R delete(@RequestBody Long[] ids) {
        gamePropertyService.deleteBatch(ids);

        return R.ok();
    }

}
