package pack;

import pack.sprites.DeadBody;
import pack.sprites.Player;
import pack.sprites.Sprite;

public class Fight {
    Player player;
    Sprite enemy;

    int playerHealthBuff;
    int playerStaminaBuff;
    int playerSpeed;
    

    public Fight(Player player, Sprite enemy) {
        this.player = player;
        this.enemy = enemy;

        playerStaminaBuff = player.stamina;
        playerHealthBuff = player.health;
        playerSpeed = player.speed;
    }

    public void doFight() {
        boolean toPrint = true;
        StringBuilder str = new StringBuilder();
        String enemyAttack = enemy.autoAttack(player);
        if(enemyAttack != null) {
            str.append(enemyAttack).append("\n");
        } else {
            toPrint = false;
        }
        String playerAttack = player.autoAttack(enemy);
        if(playerAttack != null) {
            str.append(playerAttack).append("\n");
            toPrint = true;
        } else if(!toPrint) {
            toPrint = false;
        }
        double chance = RandomNumber.getRandomNumber(0, 101);
        if(chance < 50) {
            int spell = (int) RandomNumber.getRandomNumber(0, 4);
            if(spell == 1) {
                str.append(player.useSpell("Гнев", enemy));
            } else if (spell == 2) {
                str.append(player.useSpell("Ярость", enemy));
            } else {
                str.append(player.useSpell("Блок", enemy));
            }
            toPrint = true;
        }
        if(player.health <= 0) {
            player.killed(enemy);
            player.place.deadBodies.add(new DeadBody(player));
            player.place.players.remove(player);
            Game.players.remove(player);
            Game.fights.remove(this);
            player.messageString += "loose";
            str.append("\n").append(player.name).append(Color.setColor("red")).append(" убит").append(Color.setColor("white"));
        } else if(enemy.health <= 0) {
            enemy.killed(player);
            player.place.deadBodies.add(new DeadBody(enemy));
            player.place.enemies.remove(enemy);
            Game.fights.remove(this);
            player.messageString += "win";
            str.append("\n").append(enemy.name).append(Color.setColor("red")).append(" убит").append(Color.setColor("white"));
            player.health = playerHealthBuff;
            player.stamina = playerStaminaBuff;
            player.speed = playerSpeed;
        } else {
            player.messageString += "attack";
        }
        if(toPrint) {
            player.messageString += new Frame(str.toString()).printFrame("red");
        }
    }
}
