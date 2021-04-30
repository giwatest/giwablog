package com.giwa.blog.req;

public class EbookQueryReq extends PageReq{
    private Long id;
    private Long category2Id;
    private String name;

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EbookQueryReq{");
        sb.append("id=").append(id);
        sb.append(", category2Id=").append(category2Id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}