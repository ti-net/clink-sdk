package com.tinet.clink.kb;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    //-------------------知识库API--------
    CreateArticle("kb/create_article"),
    UpdateArticle("kb/update_article"),
    DeleteArticle("kb/delete_article"),
    DescribeArticle("kb/describe_article"),
    ListArticles("kb/list_articles"),
    ListRepositories("kb/list_repositories"),
    ListDirectories("kb/list_directories"),
    //-------------------知识库API--------

    //-------------------机器人知识库API--------
    ListStandardQuestion("kb/list_standard_question"),
    CreateStandardQuestion("kb/create_standard_question"),
    UpdateStandardQuestion("kb/update_standard_question"),
    DeleteStandardQuestion("kb/delete_standard_question"),
    ListAnswer("kb/list_answer"),
    CreateAnswer("kb/create_answer"),
    UpdateAnswer("kb/update_answer"),
    DeleteAnswer("kb/delete_answer"),
    ListCorpus("kb/list_corpus"),
    CreateCorpus("kb/create_corpus"),
    UpdateCorpus("kb/update_corpus"),
    DeleteCorpus("kb/delete_corpus"),
    CreateCategory("kb/create_category"),
    UpdateCategory("kb/update_category"),
    DeleteCategory("kb/delete_category"),
    MediaUrl("kb/media_url"),
    ListFile("kb/files_list"),
    DeleteFile("kb/files_delete");

    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
