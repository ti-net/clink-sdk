package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ListChannelIvrBindingRequest;
import com.tinet.clink.livechat.request.UpdateChannelIvrBindingRequest;
import com.tinet.clink.livechat.response.ListChannelIvrBindingResponse;
import com.tinet.clink.livechat.response.UpdateChannelIvrBindingResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 渠道接入号与路由导航绑定 OpenAPI 测试。
 * 覆盖接口支持的全部渠道。精确查询和修改前，把对应 accessId 换成测试环境的接入号。
 */
public class ChatChannelIvrBindingTest {

    /** 路由导航 ID，须属于当前企业。SDK、抖音、小红书、飞书对节点类型有限制。 */
    private static final int IVR_ID = 17961;

    /** WEB、APP：渠道 accessId */
    private static final String WEB_ACCESS_ID = "f9dfca77-888f-41ef-9c4d-028b7b492798";
    private static final String APP_ACCESS_ID = "app-access-id";

    /** WEIXIN、MINIPROGRAM、ENTERPRISE_WEIXIN：授权方 appId */
    private static final String WEIXIN_ACCESS_ID = "wx3a57679b19285133";
    private static final String MINIPROGRAM_ACCESS_ID = "wx-mini-app-id";
    private static final String ENTERPRISE_WEIXIN_ACCESS_ID = "wp6302CwAAB5CdcqsMEdBMLyeJQVbn_w@@dk1fdd43df8a14a4ed";

    /** WEIBO：微博 ID */
    private static final String WEIBO_ACCESS_ID = "7465132479";

    /** BCP：百度营销 solutionId */
    private static final String BCP_ACCESS_ID = "bcp-solution-id";

    /** SDK、WX_KF、WX_KF_SUITE、FEISHU、DOUYIN、REDNOTE、ELE、DOUYIN_MINI_PROGRAM：渠道 appId */
    private static final String SDK_ACCESS_ID = "sdk-app-id";
    private static final String WX_KF_ACCESS_ID = "wxkf-app-id";
    private static final String WX_KF_SUITE_ACCESS_ID = "wxkf-suite-app-id";
    private static final String FEISHU_ACCESS_ID = "cli_xxx";
    private static final String DOUYIN_ACCESS_ID = "douyin-app-id";
    private static final String REDNOTE_ACCESS_ID = "rednote-app-id";
    private static final String ELE_ACCESS_ID = "ele-app-id";
    private static final String DOUYIN_MINI_PROGRAM_ACCESS_ID = "douyin-mini-app-id";

    protected Client client = null;
    ClientConfiguration configuration = null;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        configuration = new ClientConfiguration("7ca7a871c0b0eeb1bf356449931d3866", "Q6M35169p00893V6314T");
        configuration.setScheme("https");
        configuration.setPort(443);
        configuration.setHost("api-bj-test0.clink.cn");
        client = new Client(configuration);
    }

    @Test
    public void testListWeb() throws Exception {
        list("WEB", WEB_ACCESS_ID);
    }

    @Test
    public void testListWebPage() throws Exception {
        list("WEB", null);
    }

    @Test
    public void testUpdateWeb() throws Exception {
        update("WEB", WEB_ACCESS_ID);
    }

    @Test
    public void testListWeixin() throws Exception {
        list("WEIXIN", WEIXIN_ACCESS_ID);
    }

    @Test
    public void testListWeixinPage() throws Exception {
        list("WEIXIN", null);
    }

    @Test
    public void testUpdateWeixin() throws Exception {
        update("WEIXIN", WEIXIN_ACCESS_ID, 17485);
    }

    @Test
    public void testListMiniprogram() throws Exception {
        list("MINIPROGRAM", MINIPROGRAM_ACCESS_ID);
    }

    @Test
    public void testListMiniprogramPage() throws Exception {
        list("MINIPROGRAM", null);
    }

    @Test
    public void testUpdateMiniprogram() throws Exception {
        update("MINIPROGRAM", MINIPROGRAM_ACCESS_ID);
    }

    @Test
    public void testListWeibo() throws Exception {
        list("WEIBO", WEIBO_ACCESS_ID);
    }

    @Test
    public void testListWeiboPage() throws Exception {
        list("WEIBO", null);
    }

    @Test
    public void testUpdateWeibo() throws Exception {
        update("WEIBO", WEIBO_ACCESS_ID, 17112);
    }

    @Test
    public void testListEnterpriseWeixin() throws Exception {
        list("ENTERPRISE_WEIXIN", ENTERPRISE_WEIXIN_ACCESS_ID);
    }

    @Test
    public void testListEnterpriseWeixinPage() throws Exception {
        list("ENTERPRISE_WEIXIN", null);
    }

    @Test
    public void testUpdateEnterpriseWeixin() throws Exception {
        update("ENTERPRISE_WEIXIN", ENTERPRISE_WEIXIN_ACCESS_ID,8249);
    }

    @Test
    public void testListBcp() throws Exception {
        list("BCP", BCP_ACCESS_ID);
    }

    @Test
    public void testListBcpPage() throws Exception {
        list("BCP", null);
    }

    @Test
    public void testUpdateBcp() throws Exception {
        update("BCP", BCP_ACCESS_ID);
    }

    @Test
    public void testListSdk() throws Exception {
        list("SDK", SDK_ACCESS_ID);
    }

    @Test
    public void testListSdkPage() throws Exception {
        list("SDK", null);
    }

    @Test
    public void testUpdateSdk() throws Exception {
        update("SDK", SDK_ACCESS_ID);
    }

    @Test
    public void testListWxKf() throws Exception {
        list("WX_KF", WX_KF_ACCESS_ID);
    }

    @Test
    public void testListWxKfPage() throws Exception {
        list("WX_KF", null);
    }

    @Test
    public void testUpdateWxKf() throws Exception {
        update("WX_KF", WX_KF_ACCESS_ID);
    }

    @Test
    public void testListWxKfSuite() throws Exception {
        list("WX_KF_SUITE", WX_KF_SUITE_ACCESS_ID);
    }

    @Test
    public void testListWxKfSuitePage() throws Exception {
        list("WX_KF_SUITE", null);
    }

    @Test
    public void testUpdateWxKfSuite() throws Exception {
        update("WX_KF_SUITE", WX_KF_SUITE_ACCESS_ID);
    }

    @Test
    public void testListApp() throws Exception {
        list("APP", APP_ACCESS_ID);
    }

    @Test
    public void testListAppPage() throws Exception {
        list("APP", null);
    }

    @Test
    public void testUpdateApp() throws Exception {
        update("APP", APP_ACCESS_ID);
    }

    @Test
    public void testListFeishu() throws Exception {
        list("FEISHU", FEISHU_ACCESS_ID);
    }

    @Test
    public void testListFeishuPage() throws Exception {
        list("FEISHU", null);
    }

    @Test
    public void testUpdateFeishu() throws Exception {
        update("FEISHU", FEISHU_ACCESS_ID);
    }

    @Test
    public void testListDouyin() throws Exception {
        list("DOUYIN", DOUYIN_ACCESS_ID);
    }

    @Test
    public void testListDouyinPage() throws Exception {
        list("DOUYIN", null);
    }

    @Test
    public void testUpdateDouyin() throws Exception {
        update("DOUYIN", DOUYIN_ACCESS_ID);
    }

    @Test
    public void testListRednote() throws Exception {
        list("REDNOTE", REDNOTE_ACCESS_ID);
    }

    @Test
    public void testListRednotePage() throws Exception {
        list("REDNOTE", null);
    }

    @Test
    public void testUpdateRednote() throws Exception {
        update("REDNOTE", REDNOTE_ACCESS_ID);
    }

    @Test
    public void testListEle() throws Exception {
        list("ELE", ELE_ACCESS_ID);
    }

    @Test
    public void testListElePage() throws Exception {
        list("ELE", null);
    }

    @Test
    public void testUpdateEle() throws Exception {
        update("ELE", ELE_ACCESS_ID);
    }

    @Test
    public void testListDouyinMiniProgram() throws Exception {
        list("DOUYIN_MINI_PROGRAM", DOUYIN_MINI_PROGRAM_ACCESS_ID);
    }

    @Test
    public void testListDouyinMiniProgramPage() throws Exception {
        list("DOUYIN_MINI_PROGRAM", null);
    }

    @Test
    public void testUpdateDouyinMiniProgram() throws Exception {
        update("DOUYIN_MINI_PROGRAM", DOUYIN_MINI_PROGRAM_ACCESS_ID);
    }

    private void list(String contactType, String accessId) throws Exception {
        ListChannelIvrBindingRequest request = new ListChannelIvrBindingRequest();
        request.setContactType(contactType);
        if (accessId != null) {
            request.setAccessId(accessId);
        }
        request.setPageSize(10);
        request.setPageIndex(1);
        ListChannelIvrBindingResponse response = client.getResponseModel(request);
        System.out.println("list contactType=" + contactType + " accessId=" + accessId);
        System.out.println(mapper.writeValueAsString(response));
    }

    private void update(String contactType, String accessId) throws Exception{
        update(contactType, accessId, IVR_ID);
    }
    private void update(String contactType, String accessId, Integer ivrId) throws Exception {
        UpdateChannelIvrBindingRequest request = new UpdateChannelIvrBindingRequest();
        request.setContactType(contactType);
        request.setAccessId(accessId);
        request.setIvrId(ivrId);
        UpdateChannelIvrBindingResponse response = client.getResponseModel(request);
        System.out.println("update contactType=" + contactType + " accessId=" + accessId + " ivrId=" + IVR_ID);
        System.out.println(mapper.writeValueAsString(response));
    }
}
