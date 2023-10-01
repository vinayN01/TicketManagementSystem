class Ticket {
    private int id;
    // Code_Review: what is this username or ??
    private String user;
    private String description;
    // Code_Review: this can be enum type
    private String status;
    private String resolution;
    private String product;
    // Code_Review: Resolver id or name?? if resolver id then make this integer type
    private String resolver;

    public Ticket(int id, String user, String description, String status, String resolution, String product,
            String resolver) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.status = status;
        this.resolution = resolution;
        this.product = product;
        this.resolver = resolver;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public String getProduct() {
        return product;
    }

    public String getResolver() {
        return resolver;
    }
}
