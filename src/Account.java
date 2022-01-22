public class Account {
    private Integer money;
    public final String Owner;

    public Account(String owner) {
        Owner = owner;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return money;
    }


}
