package ProcessingServices.DateServices;

/**
 * Pattern with additional information.
 */
public class MyPattern {
    /**
     * Pattern
     */
    private String pattern;
    /**
     * Order to activate
     */
    private int order;
    /**
     * Action starting when pattern is activated
     */
    public Action action;
    public MyPattern(String pattern, int order, Action action) {
        this.pattern = pattern;
        this.order = order;
        this.action = action;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "pattern='" + pattern + '\'' +
                ", order=" + order +
                '}';
    }
}
