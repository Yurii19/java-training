public class Account {

    private Integer deposit;
    private final String OWNER_ID;

    public Account(String owner, Integer money) {
        this.OWNER_ID = owner;
        this.deposit = money;
    }

    /**
     *
     * @param money - amount of money that was removed from the account
     */
    public void putMoney(Integer money) {
        this.deposit = this.deposit + money;
    }

    /**
     *
     * @param money - amount of money that will removed from the account
     */
    public void takeMoney(Integer money) {
        this.deposit = this.deposit - money;
    }

    public Integer getDeposit() {
        return this.deposit;
    }

    public String getOwnerId() {
        return this.OWNER_ID;
    }

}
