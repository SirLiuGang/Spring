package com.cn.lg.test.callback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * @author: 刘钢
 * @Date: 2019/6/3 23:31
 * @Description:
 */
@Service(value = "ribboService")
public class RibboServiceImpl implements IRibboService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 同步调用hello
     * @return
     */
    @Override
    public String hello() {
        return restTemplate.getForEntity("http://localhost:8080/user/getUserName?userName=hello", String.class).getBody();
    }

    /**
     * 异步调用hello
     * @return
     */
    @Override
    public Future<String> syncHello() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://localhost:8080/user/getUserName?userName=syncHello", String.class).getBody();
            }
        };
    }
    // https://blog.csdn.net/youlangta/article/details/79153439
    /**
     * 异步回调hello
     * @return
     */
    @Override
    public Observable<String> callHello() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String body = restTemplate.getForEntity("http://localhost:8080/user/getUserName?userName=callHello", String.class).getBody();
                subscriber.onNext(body);
                subscriber.onCompleted();
            }
        });
    }

    /**
     * 无法访问服务
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String noHello() {
        return restTemplate.getForEntity("http://localhost:8081/user/getUserName?userName=hello", String.class).getBody();
    }

    public String helloFallBack() {
        return "error";
    }
}
