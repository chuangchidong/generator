package com.generator.controller;

import com.alibaba.fastjson.JSONObject;
import com.generator.utils.JsonFormatTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhangzhidong
 * @date 2018/5/30
 */
@RestController
public class TestController {

    @RequestMapping("/user/list")
    public Object list(@RequestParam Map<String, Object> params){


        String string = "[{name:'王大明',age:18,address:'北京市朝阳区芍药居',date:'2016-10-03'},{name:'张大刚',age:25,address:'北京市海淀区西二旗',date:'2016-10-01'},{name:'李大红',age:30,address:'上海市浦东新区世纪大道',date:'2016-10-02'},{name:'周大伟',age:26,address:'深圳市南山区深南大道',date:'2016-10-04'}]";
        return JSONObject.parseObject(string);

    }

    @RequestMapping("/user/listpage")
    public Object listpage(@RequestParam Map<String, Object> params){


        String string = "{\"status\":200,\"data\":{\"total\":86,\"users\":[{\"id\":1,\"name\":\"任敏\",\"addr\":\"福建省 南平市 建瓯市\",\"age\":47,\"birth\":\"1996-11-30\",\"sex\":1},{\"id\":2,\"name\":\"贾霞\",\"addr\":\"宁夏回族自治区 石嘴山市 惠农区\",\"age\":45,\"birth\":\"2016-07-10\",\"sex\":0},{\"id\":3,\"name\":\"白秀英\",\"addr\":\"甘肃省 临夏回族自治州 和政县\",\"age\":49,\"birth\":\"1978-10-29\",\"sex\":1},{\"id\":4,\"name\":\"魏强\",\"addr\":\"湖北省 随州市 随县\",\"age\":55,\"birth\":\"2015-12-05\",\"sex\":1},{\"id\":5,\"name\":\"贾明\",\"addr\":\"海外 海外 -\",\"age\":50,\"birth\":\"1980-09-13\",\"sex\":0},{\"id\":6,\"name\":\"贺秀兰\",\"addr\":\"江苏省 宿迁市 宿城区\",\"age\":50,\"birth\":\"1984-02-04\",\"sex\":1},{\"id\":7,\"name\":\"黄桂英\",\"addr\":\"上海 上海市 金山区\",\"age\":37,\"birth\":\"2010-04-12\",\"sex\":0},{\"id\":8,\"name\":\"孔洋\",\"addr\":\"天津 天津市 津南区\",\"age\":49,\"birth\":\"2013-04-28\",\"sex\":1},{\"id\":9,\"name\":\"邱磊\",\"addr\":\"陕西省 安康市 平利县\",\"age\":25,\"birth\":\"1983-12-02\",\"sex\":0},{\"id\":10,\"name\":\"秦磊\",\"addr\":\"新疆维吾尔自治区 克孜勒苏柯尔克孜自治州 阿图什市\",\"age\":51,\"birth\":\"1989-11-14\",\"sex\":0}]}}";
        System.out.println(JsonFormatTool.formatJson(string));

        return JSONObject.parseObject(string);

    }


}
