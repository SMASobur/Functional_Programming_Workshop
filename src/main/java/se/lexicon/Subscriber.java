package se.lexicon;

public class Subscriber {
    private int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;

    public Subscriber(int id, String email, Plan plan, boolean active, int monthsRemaining) {
        this.active = active;
        this.email = email;
        this.id = id;
        this.monthsRemaining = monthsRemaining;
        this.plan = plan;
    }

    public boolean isActive() {
        return active;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getMonthsRemaining() {
        return monthsRemaining;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setMonthsRemaining(int monthsRemaining) {
        this.monthsRemaining = monthsRemaining;
    }

    public void extendSubscription(int months) {
        this.monthsRemaining += months;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "active=" + active +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", plan=" + plan +
                ", monthsRemaining=" + monthsRemaining +
                '}';
    }
}
