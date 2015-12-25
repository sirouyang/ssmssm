package com.ssmssm.entity;

import java.math.BigDecimal;

public class Page {
    // 分页查询开始记录位置
    private Integer begin;
    
    // 分页查看下结束位置
    private Integer end;
    
    // 每页显示记录数
    private Integer length;
    
    // 查询结果总记录数
    private Integer count;
    
    // 当前页码
    private Integer current;
    
    // 总共页数
    private Integer total;
    
    public Page() {}
    
    /**
     * 构造函数
     * 
     * @param begin
     * @param length
     */
    public Page(Integer begin, Integer length) {
        this.begin = begin;
        this.length = length;
        this.end = this.begin + this.length;
        this.current =
            new BigDecimal(this.begin).divide(new BigDecimal(this.length))
                .setScale(0, BigDecimal.ROUND_DOWN)
                .intValue() + 1;
    }
    
    /**
     * @param begin
     * @param length
     * @param count
     */
    public Page(Integer begin, Integer length, Integer count) {
        this(begin, length);
        this.count = count;
    }
    
    /**
     * @return the begin
     */
    public Integer getBegin() {
        return begin;
    }
    
    /**
     * @return the end
     */
    public Integer getEnd() {
        return end;
    }
    
    /**
     * @param end the end to set
     */
    public void setEnd(Integer end) {
        this.end = end;
    }
    
    /**
     * @param begin the begin to set
     */
    public void setBegin(Integer begin) {
        this.begin = begin;
        if (this.length != 0) {
            this.current =
                new BigDecimal(this.begin).divide(new BigDecimal(this.length))
                    .setScale(0, BigDecimal.ROUND_DOWN)
                    .intValue() + 1;
        }
    }
    
    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }
    
    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
        if (this.begin != 0) {
            this.current =
                new BigDecimal(this.begin).divide(new BigDecimal(this.length))
                    .setScale(0, BigDecimal.ROUND_DOWN)
                    .intValue() + 1;
        }
    }
    
    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }
    
    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
        this.total =
            new BigDecimal(this.count).divide(new BigDecimal(this.length))
                .setScale(0, BigDecimal.ROUND_DOWN)
                .intValue();
        if (this.count % this.length != 0) {
            this.total++;
        }
    }
    
    /**
     * @return the current
     */
    public Integer getCurrent() {
        return current;
    }
    
    /**
     * @param current the current to set
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }
    
    /**
     * @return the total
     */
    public Integer getTotal() {
        if (total == 0) {
            return 1;
        }
        return total;
    }
    
    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
