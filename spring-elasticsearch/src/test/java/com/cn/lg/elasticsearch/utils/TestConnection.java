package com.cn.lg.elasticsearch.utils;

import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author: 刘钢
 * @Date: 2019/3/30 22:33
 * @Description:
 */
public class TestConnection {

    private static String host = "192.168.2.4"; // 服务器地址
    private static int port = 9300; // 端口
    TransportClient client;

    @Before
    public void connection() throws Exception {
        System.out.println("===============begin conn=============");
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));

        System.out.println("=================end conn=============");
    }


    @After
    public void close() {
        client.close();
    }

    /**
     * 组织json串, 方式1,直接拼接
     */
    private String createJson1() {
        return "{\"github\":[{\"g_name\":\"实验室官网\"},{\"g_name\":\"纳新系统\"},{\"g_name\":\"联系我们\"}],\"project\":[{\"p_name\":\"智能四旋翼无人机\"},{\"p_name\":\"爪爪智能宠物挂件\"},{\"p_name\":\"城市停车智能诱导系统\"}],\"skills\":[{\"address\":\"FZ332\",\"person\":\"张镇\",\"subject\":\"正则表达式\",\"time\":\"2018-02-24 17:33:03.0\"},{\"address\":\"FZ332\",\"person\":\"成大宝\",\"subject\":\"查找算法\",\"time\":\"2018-02-22 17:33:07.0\"},{\"address\":\"FZ332\",\"person\":\"周浩\",\"subject\":\"哈夫曼树\",\"time\":\"2018-02-28 17:12:16.0\"}],\"status\":0}";
    }

    @Test
    public void insert() {
//        String source = createJson1();
        for(int i = 0; i < 100; i++) {
            String source = "{\"github\":[{\"g_name\":\"实验室官网\"},{\"g_name\":\"纳新系统\"},{\"g_name\":\"联系我们\"}],\"project\":[{\"p_name\":\"智能四旋翼无人机\"},{\"p_name\":\"爪爪智能宠物挂件\"},{\"p_name\":\"城市停车智能诱导系统\"}],\"skills\":[{\"address\":\"FZ332\",\"person\":\"张镇"+i+"\",\"subject\":\"正则表达式\",\"time\":\"2018-02-24 17:33:03.0\"},{\"address\":\"FZ332\",\"person\":\"成大宝\",\"subject\":\"查找算法\",\"time\":\"2018-02-22 17:33:07.0\"},{\"address\":\"FZ332\",\"person\":\"周浩\",\"subject\":\"哈夫曼树\",\"time\":\"2018-02-28 17:12:16.0\"}],\"status\":0}";
            IndexResponse response = client.prepareIndex("twitter", "tweet", String.valueOf(i)).setSource(source).get();

            // 存json入索引中
    //        // 结果获取
            System.out.println("结果：" + response.toString());
        }
    }

    @Test
    public void get() {
        GetResponse getResponse = client.prepareGet("twitter", "tweet", "99").get();
        System.out.println("索引库的数据:" + getResponse.getSourceAsString());
    }

    @Test
    public void search() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("{\"query\":{\"bool\":{\"filter\":[{\"bool\":{\"must\":[{\"match_phrase\":{\"person\":{\"query\":\"u2018u5f20u954799u2019\"}}}]}}]}},\"_source\":{\"include\":[\"*\"]}}");
//        WrapperQueryBuilder wqb = QueryBuilders.wrapperQuery(sb.toString());
//        SearchResponse searchResponse = client.prepareSearch("twitter")
//                .setTypes("tweet").setQuery(wqb).setSize(10).get();
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        for(SearchHit hit : hits){
//            String content = hit.getSourceAsString();
//            System.out.println(content);
//        }
        QueryBuilder qb = QueryBuilders.wrapperQuery("{\n" +
                "    \"query\": {\n" +
                "        \"matach_all\": {\n" +
                "            \"person\": \"张镇99\"\n" +
                "        }\n" +
                "    }\n" +
                "}");


        SearchResponse searchResponse = client.prepareSearch("twitter").setTypes("tweet")
//                .setQuery(QueryBuilders.termsQuery("person", "张镇99"))
//                .setQuery(QueryBuilders.matchQuery("status", "0"))
                .setQuery(qb)
                .setFrom(0)
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println("total:" + hits.getTotalHits());
        for(SearchHit hit : hits){
            String content = hit.getSourceAsString();
            System.out.println("----：" + content);
        }
    }




























}
