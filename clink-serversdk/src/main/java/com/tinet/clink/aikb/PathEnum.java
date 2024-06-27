package com.tinet.clink.aikb;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    //-------------------会话问答API--------
    ChatConversation("aikb/chat_conversation_on_open"),
    ChatListConversations("aikb/list_conversations"),
    ChatDescribeConversation("aikb/describe_conversation"),
    ChatUpdateConversation("aikb/update_conversation"),
    ChatDeleteConversation("aikb/delete_conversation"),
    SearchKnowledgeOnOpen("aikb/search_knowledge_on_open"),
    DescribeFileMediaUrl("aikb/describe_file_media_url"),
    DescribeFaqMediaUrl("aikb/describe_faq_media_url")

    ;

    //-------------------知识库API--------



    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
