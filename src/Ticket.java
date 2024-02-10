class Ticket {
    private int id;
    private String user;
    private String description;
    private String status;
    private String resolution;
    private String product;
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
