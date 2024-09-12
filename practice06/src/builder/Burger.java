package builder;

class Burger {
    private String bun;
    private String meat;
    private String cheese;
    private String vegetables;

    private Burger(Builder builder) {
        this.bun = builder.bun;
        this.meat = builder.meat;
        this.cheese = builder.cheese;
        this.vegetables = builder.vegetables;
    }

    public static class Builder {
        private String bun;
        private String meat;
        private String cheese;
        private String vegetables;

        public Builder setBun(String bun) {
            this.bun = bun;
            return this;
        }

        public Builder setMeat(String meat) {
            this.meat = meat;
            return this;
        }

        public Builder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder setVegetables(String vegetables) {
            this.vegetables = vegetables;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}
