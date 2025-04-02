package FightingGame.Model;
public interface Hittable {
    void attack(int x, int y, int damage, String moveType, String d);
    void checkHit(int x, int y, int damage, String moveType, String d);
}