package top.wujinxing.marshome.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.wujinxing.marshome.entity.Home;
import top.wujinxing.marshome.mapper.HomeMapper;
import top.wujinxing.marshome.service.HomeService;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements HomeService {

    @Override
    public List<Home> list() {
        log.info("home {}", "start");
        LambdaQueryWrapper<Home> wrapper = Wrappers.lambdaQuery();
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public Home selectByName(String name) {
        LambdaQueryWrapper<Home> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Home::getName, name);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public boolean add(Home home) {
        this.baseMapper.insert(home);
        return true;
    }

    @Override
    public Page<Home> pageList(Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<Home> wrapper = Wrappers.lambdaQuery();
        return this.baseMapper.selectPage(new Page<>(pageNo, pageSize), wrapper);
    }

}
