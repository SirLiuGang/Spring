package com.cn.lg.test.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Subscriber;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author: 刘钢
 * @Date: 2019/6/3 23:31
 * @Description:
 */
@RestController
@RequestMapping("ribbo")
public class RibboController {

    private static final Logger LOG = LoggerFactory.getLogger(RibboController.class);

    @Resource(name = "ribboService")
    private IRibboService ribboService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return ribboService.hello();
    }

    @RequestMapping(value = "syncHello", method = RequestMethod.GET)
    public String syncHello() throws ExecutionException, InterruptedException {
        return ribboService.syncHello().get();
    }

    @RequestMapping(value = "callHello", method = RequestMethod.GET)
    public String callHello() {
        ribboService.callHello().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LOG.info("完成");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                LOG.info("接收到了：{}", s);
            }

        });

        return "ok";
    }

    @RequestMapping(value = "noHello", method = RequestMethod.GET)
    public String noHello() {
        return ribboService.noHello();
    }

}
