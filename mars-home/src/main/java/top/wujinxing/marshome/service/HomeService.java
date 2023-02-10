package top.wujinxing.marshome.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.wujinxing.marshome.entity.Home;

public interface HomeService {
    
    List<Home> list();
    
    Home selectByName(String name);

    boolean add(Home home);

    Page<Home> pageList(Integer pageNo, Integer pageSize);

}
