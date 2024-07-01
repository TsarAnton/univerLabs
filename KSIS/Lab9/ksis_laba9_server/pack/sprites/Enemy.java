package pack.sprites;

import pack.Color;
import pack.RandomNumber;
import pack.weapons.*;

public class Enemy extends Sprite {

    public Enemy() {
        double chance = RandomNumber.getRandomNumber(0, 101);

        if(chance <= 50) {
            this.name = "Слабый враг";
            this.health = 30;
            this.armor = 2;
            this.speed = 8;
            this.attack = 11;
            this.critChance = 5;
            this.killExp = 20;

            double chance1 = RandomNumber.getRandomNumber(0, 101);
            if(chance1 <= 70) {
                double chance2 = RandomNumber.getRandomNumber(0, 101);
                if(chance2 <= 25) {
                    double chance3 = RandomNumber.getRandomNumber(0, 101);
                    if(chance3 <= 70) {
                        this.inventory.add(new SmallShield());
                    } else if(chance3 <= 90) {
                        this.inventory.add(new MediumShield());
                    } else {
                        this.inventory.add(new HeavyShield());
                    }
                } else if(chance2 <= 50) {
                    double chance3 = RandomNumber.getRandomNumber(0, 101);
                    if(chance3 <= 70) {
                        this.inventory.add(new SimpleSword());
                    } else if(chance3 <= 90) {
                        this.inventory.add(new GoodSword());
                    } else {
                        this.inventory.add(new LegendarySword());
                    }
                } else if(chance2 <= 75) {
                    double chance3 = RandomNumber.getRandomNumber(0, 101);
                    if(chance3 <= 70) {
                        this.inventory.add(new SimpleRing());
                    } else if(chance3 <= 90) {
                        this.inventory.add(new GoodRing());
                    } else {
                        this.inventory.add(new LegendaryRing());
                    }
                } else {
                    double chance3 = RandomNumber.getRandomNumber(0, 101);
                    if(chance3 <= 70) {
                        this.inventory.add(new SimpleArmor());
                    } else if(chance3 <= 90) {
                        this.inventory.add(new GoodArmor());
                    } else {
                        this.inventory.add(new LegendaryArmor());
                    }
                }
            }
        } else if(chance <= 80) {
            this.name = "Обычный враг";
            this.health = 60;
            this.armor = 10;
            this.attack = 30;
            this.speed = 5;
            this.critChance = 10;
            this.killExp = 50;

            double chance1 = RandomNumber.getRandomNumber(0, 101);
            if(chance1 <= 60) {
                double chance1_1 = (int) RandomNumber.getRandomNumber(1, 3);
                for(int i = 0; i < chance1_1; i++) {
                    double chance2 = RandomNumber.getRandomNumber(0, 101);
                    if(chance2 <= 25) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 60) {
                            this.inventory.add(new SmallShield());
                        } else if(chance3 <= 80) {
                            this.inventory.add(new MediumShield());
                        } else {
                            this.inventory.add(new HeavyShield());
                        }
                    } else if(chance2 <= 50) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 60) {
                            this.inventory.add(new SimpleSword());
                        } else if(chance3 <= 80) {
                            this.inventory.add(new GoodSword());
                        } else {
                            this.inventory.add(new LegendarySword());
                        }
                    } else if(chance2 <= 75) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 60) {
                            this.inventory.add(new SimpleRing());
                        } else if(chance3 <= 80) {
                            this.inventory.add(new GoodRing());
                        } else {
                            this.inventory.add(new LegendaryRing());
                        }
                    } else {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 60) {
                            this.inventory.add(new SimpleArmor());
                        } else if(chance3 <= 80) {
                            this.inventory.add(new GoodArmor());
                        } else {
                            this.inventory.add(new LegendaryArmor());
                        }
                    }
                }
            }
        } else {
            this.name = "Сильный враг";
            this.health = 100;
            this.attack = 50;
            this.armor = 15;
            this.speed = 3;
            this.critChance = 15;
            this.killExp = 100;

            double chance1 = RandomNumber.getRandomNumber(0, 101);
            if(chance1 <= 50) {
                double chance1_1 = (int) RandomNumber.getRandomNumber(1, 4);
                for(int i = 0; i < chance1_1; i++) {
                    double chance2 = RandomNumber.getRandomNumber(0, 101);
                    if(chance2 <= 25) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 50) {
                            this.inventory.add(new SmallShield());
                        } else if(chance3 <= 70) {
                            this.inventory.add(new MediumShield());
                        } else {
                            this.inventory.add(new HeavyShield());
                        }
                    } else if(chance2 <= 50) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 50) {
                            this.inventory.add(new SimpleSword());
                        } else if(chance3 <= 70) {
                            this.inventory.add(new GoodSword());
                        } else {
                            this.inventory.add(new LegendarySword());
                        }
                    } else if(chance2 <= 75) {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 50) {
                            this.inventory.add(new SimpleRing());
                        } else if(chance3 <= 70) {
                            this.inventory.add(new GoodRing());
                        } else {
                            this.inventory.add(new LegendaryRing());
                        }
                    } else {
                        double chance3 = RandomNumber.getRandomNumber(0, 101);
                        if(chance3 <= 50) {
                            this.inventory.add(new SimpleArmor());
                        } else if(chance3 <= 70) {
                            this.inventory.add(new GoodArmor());
                        } else {
                            this.inventory.add(new LegendaryArmor());
                        }
                    }
                }
            }
        }
    }

    @Override
    public String autoAttack(Sprite enemy) {
        if(currentTime == 0) {
            currentTime = speed;
            int delta = this.attack - enemy.armor;
            int critAttack = 0;
            int damage = 0;
            double crit = RandomNumber.getRandomNumber(0, 101);
            if(this.critChance >= crit) {
                double critBonus = RandomNumber.getRandomNumber(1, 11);
                critAttack = (int) critBonus;
            }
            if(delta > 0) {
                double chance = RandomNumber.getRandomNumber(0, 101);
                double minusAttack = RandomNumber.getRandomNumber(1, 6);
                if(chance < 50) {
                    damage = (int) (this.attack + critAttack - minusAttack - (enemy.armor / 2));
                } else if(chance < 80) {
                    damage = (int) (this.attack + critAttack - (enemy.armor / 2));
                } else {
                    damage = (int) (this.attack + critAttack + minusAttack - (enemy.armor /2));
                }
            } else {
                int p = (int) (Math.pow(Math.E, delta/10) * 100);

                if(p >= RandomNumber.getRandomNumber(0, 101)) {
                    damage = critAttack + 1;
                }
            }
            if(damage < 0) {
                damage = 0;
            }
            enemy.health -= damage;
            return this.name + " наносит " + enemy.name + " урон " + Color.setColor("red") + damage + Color.setColor("white") + " {"  + Color.setColor("green") + enemy.health  + Color.setColor("white") + "}";
        } else {
            currentTime--;
            return null;
        }
    }

    @Override
    public String useSpell(String name, Sprite enemy) {
        // TODO Auto-generated method stub
        return "";
        
    }
}
