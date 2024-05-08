package player;

import equipment.Amulet;
import equipment.Armor;
import item.Item;
import unit.BasedUnit;
import utils.UnitUtils;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Player {
    private int level;
    private int hp;
    private Point2D position;
    private Wallet wallet;
    private PlayerEquipment equipment;
    private int power;
    private static int score;
    private ArrayList<Item> Inventory;

    private int attack(BasedUnit unit){
        int calculatedDamage = 0;
//        if(UnitUtils.evaluate() == 1){}
        return calculatedDamage;
    }
}
