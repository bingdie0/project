package com.my.project.service.impl;

import com.my.project.dao.UrlMapper;
import com.my.project.entity.Url;
import com.my.project.service.AutoAddUrlInfoService;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Objects;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-25
 **/
@Service
public class AutoAddUrlInfoServiceImpl implements AutoAddUrlInfoService {

    @Autowired
    private UrlMapper urlMapper;

    private Site site;

    public AutoAddUrlInfoServiceImpl() {
        this.site = Site.me().setRetryTimes(3).setSleepTime(100);
    }

    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
        Url urlEntity = new Url();
        urlEntity.setUrl(url);
        urlEntity = urlMapper.selectOne(urlEntity);

        String shopName, price = null, title, listPic = null;
        Html html = page.getHtml();

        Selectable imgSelectable = html.xpath("ul[@id='J_UlThumb']/li[@class='tb-selected']/a/img");
        HtmlNode node = null;
        if (imgSelectable instanceof HtmlNode) {
            node = (HtmlNode) imgSelectable;
        }
        if (Objects.nonNull(node) && node.getElements().size() > 0) {
            // 天猫详情
            imgSelectable = imgSelectable.nodes().get(0);
            if (imgSelectable instanceof HtmlNode) {
                HtmlNode imgNode = (HtmlNode) imgSelectable;
                Element element = imgNode.getElements().get(0);
                listPic = element.attr("src");
            }
            shopName = html.xpath("a[@class='slogo-shopname']/strong/text()").toString();
//            price = html.xpath("dt[@class='tb-metatit']").toString();
            title = html.xpath("div[@class='tb-detail-hd']/h1/text()").toString().trim();
            /*try {
                String url = "http://mdskip.taobao.com/core/initItemDetail.htm?isRegionLevel=true&itemTags=385,775,843,1035,1163,1227,1478,1483,1539,1611,1863,1867,1923,2049,2059,2242,2251,2315,2507,2635,3595,3974,4166,4299,4555,4811,5259,5323,5515,6145,6785,7809,9153,11265,12353,12609,13697,13953,16321,16513,17473,17537,17665,17857,18945,19841,20289,21762,21826,25922,28802,53954&tgTag=false&addressLevel=4&isAreaSell=false&sellerPreview=false&offlineShop=false&showShopProm=false&isIFC=false&service3C=true&isSecKill=false&isForbidBuyItem=false&cartEnable=true&sellerUserTag=839979040&queryMemberRight=true&itemId=40533381395&sellerUserTag2=306250462070310924&household=false&isApparel=false¬AllowOriginPrice=false&tmallBuySupport=true&sellerUserTag3=144467169269284992&sellerUserTag4=1152930305168967075&progressiveSupport=true&isUseInventoryCenter=false&tryBeforeBuy=false&callback=setMdskip×tamp=1420351892310";

                HttpClientBuilder builder = HttpClients.custom();
                builder.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
                CloseableHttpClient httpClient = builder.build();
                final HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("Referer", "http://detail.tmall.com/item.htm?id=40533381395&skuId=68347779144&areaId=110000&cat_id=50024400&rn=763d147479ecdc17c2632a4219ce96b3&standard=1&user_id=263726286&is_b=1");
                CloseableHttpResponse response = null;
                response = httpClient.execute(httpGet);
                final HttpEntity entity = response.getEntity();
                String result = null;
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    EntityUtils.consume(entity);
                }


                //商品价格的返回值，需要解析出来价格

                result = result.substring(10, result.length() - 1);

                JSONObject object = JSON.parseObject(result);
                JSONObject object2 = (JSONObject) object.get("defaultModel");
                JSONObject object3 = (JSONObject) object2.get("itemPriceResultDO");
                JSONObject object4 = (JSONObject) object3.get("priceInfo");
                JSONObject object5 = (JSONObject) object4.get("68347779144");
                JSONArray jsonArray = JSON.parseArray(object5.get("promotionList").toString());
                if (jsonArray.size() == 1) {
                    JSONObject object6 = (JSONObject) jsonArray.get(0);
                    System.out.println(object6.get("price"));
                }
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else {
            // 普通淘宝详情
            imgSelectable = page.getHtml().xpath("ul[@id='J_UlThumb']/li[@class='tb-selected']/div/a/img");
            if (imgSelectable instanceof HtmlNode) {
                HtmlNode htmlNode = (HtmlNode) imgSelectable;
                Element element = htmlNode.getElements().get(0);
                listPic = element.attr("data-src");
            }
            shopName = html.xpath("div[@class='tb-shop-name']/dl/dd/strong/a/text()").toString();
            price = html.xpath("strong[@id='J_StrPrice']/em[@class='tb-rmb-num']/tidyText()").toString();
            title = page.getHtml().xpath("//h3[@class='tb-main-title']/text()").toString();
        }

        urlEntity.setListPic(listPic);
        urlEntity.setPrice(price != null ? Double.parseDouble(price) : null);
        urlEntity.setShopName(shopName);
        urlEntity.setTitle(title);
        urlMapper.updateByPrimaryKey(urlEntity);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        /*String detailUrl = "https://detail.tmall.com/item.htm?id=555918005495";
        String detailUrl2 = "https://item.taobao.com/item.htm?spm=a219r.lm874.14.15.685d1bf42t9mVN&id=576245292245&ns=1&abbucket=2";*/
        Spider.create(new AutoAddUrlInfoServiceImpl()).addUrl("https://item.taobao.com/item.htm?id=577345730738").run();
        /*try {
            String url = "https://item.taobao.com/item.htm?id=577345730738";
            HttpClientBuilder builder = HttpClients.custom();
            builder.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
            CloseableHttpClient httpClient = builder.build();
            final HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                EntityUtils.consume(entity);
            }
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
