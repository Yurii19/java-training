public class Account {

    private Integer money;
    private final String OWNER_ID;

    public Account(String owner, Integer money) {
        this.OWNER_ID = owner;
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

    public String getOWNER_ID() {
        return OWNER_ID;
    }

}
