package cn.hot.hotdog.controller;

import cn.hot.hotdog.service.IMediaTypeService;
import cn.hot.hotdog.domain.MediaType;
import cn.hot.hotdog.query.MediaTypeQuery;
import cn.hot.hotdog.util.AjaxResult;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediaType")
public class MediaTypeController {
    @Autowired
    public IMediaTypeService mediaTypeService;

    /**
    * 保存和修改公用的
    * @param mediaType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody MediaType mediaType){
        try {
            if(mediaType.getId()!=null){
                mediaTypeService.updateById(mediaType);
            }else{
                mediaTypeService.insert(mediaType);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            mediaTypeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public MediaType get(@PathVariable("id")Long id)
    {
        return mediaTypeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<MediaType> list(){

        return mediaTypeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<MediaType> json(@RequestBody MediaTypeQuery query)
    {
        Page<MediaType> page = new Page<MediaType>(query.getPage(),query.getRows());
            page = mediaTypeService.selectPage(page);
            return new PageList<MediaType>(page.getTotal(),page.getRecords());
    }
}
