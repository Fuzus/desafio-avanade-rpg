package br.com.fuzus.avanadedesafiorpg.domain.character.entity;

public enum Dice {
    D12(12),
    D8(8),
    D6(6),
    D4(4);

    public final int faces;

    public static Dice getDice(int faces) {
        for (Dice dice : values()) {
            if (dice.faces == faces) {
                return dice;
            }
        }
        return null;
    }

    Dice(int faces) {
        this.faces = faces;
    }
}
