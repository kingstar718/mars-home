package top.wujinxing.marshome.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.RequiredArgsConstructor;
import top.wujinxing.marshome.entity.Home;
import top.wujinxing.marshome.service.HomeService;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HelloController {
    
    private final HomeService homeService;

    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        List<Home> list = homeService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Home home) {
        boolean add = homeService.add(home);
        return ResponseEntity.ok(add);
    }

    @GetMapping("/page")
    public ResponseEntity<Object> page(Integer pageNo, Integer pageSize) {
        Page<Home> pageList = homeService.pageList(pageNo, pageSize);
        return ResponseEntity.ok(pageList);
    }

    @GetMapping("/selectByName")
    public ResponseEntity<Object> selectByName(String name) {
        Home home = homeService.selectByName(name);
        return ResponseEntity.ok(home);
    }

}
