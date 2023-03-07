package top.wujinxing.marshome.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import top.wujinxing.marshome.entity.Home;
import top.wujinxing.marshome.service.HomeService;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {
    
    private final HomeService homeService;

    @GetMapping("/addPolygon")
    public ResponseEntity<Object> addPolygon(String points) {
        Home home = new Home();
        home.setName(UUID.randomUUID().toString().replace("-", ""));
        home.setPolygon(points);
        boolean success = homeService.add(home);
        if (!success) {
            return ResponseEntity.ok("添加失败");
        }
        return ResponseEntity.ok("添加成功");
    }
    
}
