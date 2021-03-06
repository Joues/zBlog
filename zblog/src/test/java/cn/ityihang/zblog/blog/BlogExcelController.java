package cn.ityihang.zblog.blog;


import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.service.IBlogService;
import cn.ityihang.zblog.utils.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Excel导入导出")
@RestController
@RequestMapping("/excel")
public class BlogExcelController {

    @Autowired
    IBlogService blogService;

    /**
     * Excel導入導出工具
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "excel导入导出工具")
    @GetMapping(value = "/exportExcel")
    public String exportExcel(HttpServletResponse response) throws Exception {
        LambdaQueryWrapper<BlogInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BlogInfo::getId, "1");
        List<BlogInfo> blogInfoList = blogService.list(wrapper);

        String fileName = "博客表";

        Map<String, Object> studentMap = new HashMap();
        studentMap.put("entity", BlogInfo.class);
        studentMap.put("dataList", blogInfoList);
        studentMap.put("fileName", fileName);

        List<Map> mapList = new ArrayList();
        mapList.add(studentMap);
        ExcelUtil.exportMultisheetExcel(fileName, mapList, response);
        return "success";
    }

    @GetMapping(value = "/readExcel")
    public String readExcel() throws Exception {
        String filePath = "D:\\student.xls";
        List<Map<String, String>> mapList = ExcelUtil.readExcel(filePath, 0);
        return "success";
    }

}
