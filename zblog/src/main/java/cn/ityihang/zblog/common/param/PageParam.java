package cn.ityihang.zblog.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Optional;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;


@Data
@ApiModel("分页参数对象")
public class PageParam implements Serializable {

    @ApiModelProperty(value = "页码,默认为1,最小为1", example = "1")
    @Min(1)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小,默认为10，最小为10", example = "10")
    @Range(min = 10)
    private Integer limit;

    @ApiModelProperty("搜索字符串")
    private String keyword;


    public Integer getPageNo(){
        pageNo = Optional.ofNullable(pageNo).orElse(1);
        return pageNo;
    }

    public Integer getLimit(){
        limit = Optional.ofNullable(limit).orElse(10);
        return limit;
    }
}
