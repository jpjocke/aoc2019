package org.advent;

public class Material {
    private long amount;
    private String name;

    public Material(long amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public Material copy(int times) {
        return new Material(times * amount, name);
    }

    public long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public long take(long take) {
        long orig = amount;
        amount = Math.max(amount - take, 0);
        if (amount == 0){
            return orig;
        }
        return take;
    }

    public boolean add(Material m) {
        if (isSameMaterial(m)) {
            amount += m.getAmount();
            return true;
        }
        return false;
    }

    public boolean isSameMaterial(Material m) {
        return m.getName().equals(name);
    }

    public boolean isOre() {
        return name.equals("ORE");
    }


    public boolean isFuel() {
        return name.equals("FUEL");
    }

    @Override
    public String toString() {
        return "{"  + amount +
                ", " + name + '}';
    }
}
