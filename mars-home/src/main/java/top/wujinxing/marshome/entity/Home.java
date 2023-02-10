package top.wujinxing.marshome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Home {
    
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String polygon;

}
