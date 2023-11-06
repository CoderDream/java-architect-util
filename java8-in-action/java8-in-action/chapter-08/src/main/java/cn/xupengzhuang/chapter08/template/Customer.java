package cn.xupengzhuang.chapter08.template;

public class Customer {
    private Long id;
    private String name;
    private String account;

    public Customer() {
    }

    public Customer(Long id, String name, String account) {
        this.id = id;
        this.name = name;
        this.account = account;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
