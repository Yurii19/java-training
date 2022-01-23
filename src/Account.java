public class Account {
    private Integer money;
    public final String OwnerID;

    public Account(String owner, Integer money) {
        this.OwnerID = owner;
        this.money = money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return money;
    }


}
