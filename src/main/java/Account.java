public class Account {

    private int deposit;
    private final String OWNER_ID;

    public Account(String owner, int money) {
        this.OWNER_ID = owner;
        this.deposit = money;
    }

    /**
     *
     * @param money - amount of money that was removed from the account
     */
    public void putMoney(int money) {
        this.deposit = this.deposit + money;
    }

    /**
     *
     * @param money - amount of money that will removed from the account
     */
    public void takeMoney(int money) {
        this.deposit = this.deposit - money;
    }

    public Integer getDeposit() {
        return this.deposit;
    }

    public String getOwnerId() {
        return this.OWNER_ID;
    }

}
