public class Account {

    private Integer money;
    private final String OWNER_ID;

    public Account(String owner, Integer money) {
        this.OWNER_ID = owner;
        this.money = money;
    }

    /**
     *
     * @param money - amount of money that was removed from the account
     */
    public void putMoney(Integer money) {
        this.money = this.money + money;
    }

    /**
     *
     * @param money - amount of money that will removed from the account
     */
    public void takeMoney(Integer money) {
        this.money = this.money - money;
    }

    public Integer getMoney() {
        return money;
    }

    public String getOWNER_ID() {
        return this.OWNER_ID;
    }

}
