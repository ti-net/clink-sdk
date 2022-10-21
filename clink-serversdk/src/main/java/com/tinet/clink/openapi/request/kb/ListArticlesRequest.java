package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListArticlesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 知识库文章列表请求
 *
 * @author feizq
 * @date 2021/06/25
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListArticlesRequest extends AbstractRequestModel<ListArticlesResponse> {

    /**
     * 知识库 Id
     */
    private Integer kbId;

    /**
     * 知识库类型 0：问答库；1：文档库；2：文件库
     */
    private Integer kbType;

    /**
     * 目录 Id
     */
    private Integer directoryId;

    /**
     * 渠道类型 content、phoneContent、onlineContent、wechatContent、appContent、webContent
     */
    private String channelType;

    /**
     * 查询的关键字
     */
    private String keyword;

    /**
     * 排序字段   none:默认顺序 heat:热度排序 like:点赞排序 dislike:点踩排序
     */
    private String order;

    /**
     * 分页查询的偏移量
     */
    private Integer offset;

    /**
     * 分页查询的查询条数
     */
    private Integer limit;

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
        if (kbId != null) {
            putQueryParameter("kbId", kbId);
        }
    }

    public Integer getKbType() {
        return kbType;
    }

    public void setKbType(Integer kbType) {
        this.kbType = kbType;
        if (kbType != null) {
            putQueryParameter("kbType", kbType);
        }
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
        if (directoryId != null) {
            putQueryParameter("directoryId", directoryId);
        }
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
        if (channelType != null) {
            putQueryParameter("channelType", channelType);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (keyword != null) {
            putQueryParameter("keyword", keyword);
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
        if (order != null) {
            putQueryParameter("order", order);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public ListArticlesRequest() {
        super(PathEnum.ListArticles.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListArticlesResponse> getResponseClass() {
        return ListArticlesResponse.class;
    }
}
