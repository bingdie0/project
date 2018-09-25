package com.my.project.service.impl;

import com.my.project.service.AutoAddUrlInfoService;
import org.jsoup.nodes.Element;
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

    private Site site;

    public AutoAddUrlInfoServiceImpl() {
        this.site = Site.me().setRetryTimes(3).setSleepTime(100);
    }

    @Override
    public void process(Page page) {
        String shopName, price, title, listPic = null;
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
            price = html.xpath("dt[@class='tb-metatit']").toString();
            title = html.xpath("div[@class='tb-detail-hd']/h1/text()").toString().trim();
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
        System.out.println(listPic);
        System.out.println(shopName);
        System.out.println(price);
        System.out.println(title);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String detailUrl = "https://detail.tmall.com/item.htm?id=555918005495";
        String detailUrl2 = "https://item.taobao.com/item.htm?spm=a219r.lm874.14.15.685d1bf42t9mVN&id=576245292245&ns=1&abbucket=2";
        Spider.create(new AutoAddUrlInfoServiceImpl()).addUrl(detailUrl).run();
    }
}
