public class Account {
    private Integer money;
    public final String OwnerID;

    public Account(String owner, Integer money) {
        this.OwnerID = owner;
        this.money = money;
    }

    public void putMoney(Integer money) {
        this.money = this.money + money;
    }

    public void takeMoney(Integer money) {
        this.money = this.money - money;
    }

    public Integer getMoney() {
        return money;
    }


}
