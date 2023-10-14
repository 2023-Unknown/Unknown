package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unknown.backend.dev.service.RedisService;

@RestController
@Api(tags = {"RedisTestAPI"})
public class RedisController {
    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService){
        this.redisService = redisService;
    }

    @PostMapping("/redis/save")
    @ApiOperation(value = "Redis 저장 API", notes = "Redis에 데이터를 저장합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "저장할 데이터의 키", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "value", value = "저장할 데이터의 값", required = true, dataType = "string", paramType = "path")
    })
    public String saveData(@RequestParam String key, @RequestParam String value){
        redisService.saveData(key, value);
        return "success";
    }

    @GetMapping("/redis/show")
    @ApiOperation(value = "Redis 조회 API", notes = "Redis에 저장된 데이터를 조회합니다.")
    @ApiImplicitParam(name = "key", value = "조회할 데이터의 키", required = true, dataType = "string", paramType = "path")
    public String showData(@RequestParam String key){
        return redisService.showData(key);
    }


}
