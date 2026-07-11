package org.example;
import java.time.LocalDateTime;
import java.util.List;

public class Document {
    private String docId;
    private String title;
    private String content;
    private List<String> tags;
    LocalDateTime createdAt;

    public Document(String docId,String title,String content,List<String> tags,LocalDateTime createdAt){
        this.docId=docId;
        this.title=title;
        this.content=content;
        this.tags=tags;
        this.createdAt = createdAt;
    }

    public String getDocId() {
        return docId;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
