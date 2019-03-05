package cn.hot.hotdog.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author xvbo
 * @since 2019-03-05
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 规格名称
     */
    private String specName;
    @TableField("product_type_id")
    private Long productTypeId;
    /**
     * 1：显示属性，2sku属性
     */
    private Integer type;
    @TableField(exist = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
        ", id=" + id +
        ", specName=" + specName +
        ", productTypeId=" + productTypeId +
        ", type=" + type +
        "}";
    }
}
