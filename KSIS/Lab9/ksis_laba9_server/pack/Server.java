package pack;

import java.io.*;
import java.net.*;

//import pack.map.*;
//import pack.skills.*;
import pack.sprites.*;

public class Server extends Thread {

    private String TEMPL_CONN = "Клиент '%d' отключился";

    private  Socket socket;
    private  int num;

    public Server() {}
    public void setSocket(int num, Socket socket) {
        this.num = num;
        this.socket = socket;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void run() {
        try {
            InputStream  sin  = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream  dis = new DataInputStream (sin );
            DataOutputStream dos = new DataOutputStream(sout);

            dos.writeUTF(new Frame("Добро пожаловать!\nДля начала игры введите свое имя.").printFrame("green"));
            dos.flush();
            String name = null;
            while(true) {
                name = dis.readUTF();
                if(!name.isEmpty()) {
                    break;
                }
            }
            dos.writeUTF(new Frame("Ваше имя: " + name + "\nПриятной игры!").printFrame("green"));
            dos.flush();
            Player player = new Player(name, dos);
            Game.players.add(player);
            boolean con = true;
            String line = null;
            
            while(con) {
                line = dis.readUTF();//читаем
                String[] arguments = line.split(" ");
                switch(arguments[0]){
                    case("выход"):
                        dos.writeUTF(new Frame("Спасибо за игру!").printFrame("green"));
                        Game.players.remove(player);
                        player.place.players.remove(player);
                        socket.close();
                        con = false;
                        System.out.println(String.format(TEMPL_CONN, num));
                        break;
                    case("статус"):
                        player.messageString += new Frame(player.toString()).printFrame("dark blue");
                        break;
                    case("смотреть"):
                        player.messageString += new Frame(player.place.toString(player)).printFrame("blue");
                        break;
                    case("запад"):
                        if(player.place.east != null) {
                            player.place.players.remove(player);
                            player.place = player.place.east;
                            player.place.players.add(player);
                            player.messageString += new Frame(player.place.toString(player)).printFrame("blue");
                        } else {
                            player.messageString += new Frame("Вы не можете пойти на запад").printFrame("blue");
                        }
                        break;
                    case("восток"):
                        if(player.place.west != null) {
                            player.place.players.remove(player);
                            player.place = player.place.west;
                            player.place.players.add(player);
                            player.messageString += new Frame(player.place.toString(player)).printFrame("blue");
                        } else {
                            player.messageString += new Frame("Вы не можете пойти на восток").printFrame("blue");
                        }
                        break;
                    case("север"):
                        if(player.place.north != null) {
                            player.place.players.remove(player);
                            player.place = player.place.north;
                            player.place.players.add(player);
                            player.messageString += new Frame(player.place.toString(player)).printFrame("blue");
                        } else {
                            player.messageString += new Frame("Вы не можете пойти на север").printFrame("blue");
                        }
                        break;
                    case("юг"):
                        if(player.place.south != null) {
                            player.place.players.remove(player);
                            player.place = player.place.south;
                            player.place.players.add(player);
                            player.messageString += new Frame(player.place.toString(player)).printFrame("blue");
                        } else {
                            player.messageString += new Frame("Вы не можете пойти на юг").printFrame("blue");
                        }
                        break;
                    case("описание"):
                        if(arguments.length == 1 || arguments.length > 2) {
                                player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                            } else {
                            String arg = arguments[1];
                            try {
                                int id = Integer.parseInt(arg);
                                if(id < 0 || id >= player.inventory.size() || player.inventory.isEmpty()) {
                                    player.messageString += new Frame("Введен неправильный id предмета").printFrame("red");
                                } else {
                                    player.messageString += new Frame(player.inventory.get(id).toString()).printFrame("dark blue");
                                }
                            } catch(NumberFormatException e) {
                                if(arg.equals("Гнев")) {
                                    player.messageString += new Frame(player.strongAttack.toString()).printFrame("dark blue");
                                } else if(arg.equals("Ярость")) {
                                    player.messageString += new Frame(player.angry.toString()).printFrame("dark blue");
                                } else if(arg.equals("Блок")) {
                                    player.messageString += new Frame(player.block.toString()).printFrame("dark blue");
                                } else {
                                    player.messageString += new Frame("Неверное название способности").printFrame("red");
                                }
                            }
                        }
                        break;
                    case("надеть"):
                        if(arguments.length == 1 || arguments.length > 2) {
                            player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                        } else {
                            String arg = arguments[1];
                            try {
                                int id = Integer.parseInt(arg);
                                if(id < 0 || id >= player.inventory.size() || player.inventory.isEmpty()) {
                                    player.messageString += new Frame("Введен неправильный id предмета").printFrame("red");
                                } else {
                                    player.messageString += new Frame(player.equip(player.inventory.get(id))).printFrame("dark blue");
                                }
                            } catch(NumberFormatException e) {
                                player.messageString += new Frame("Нужно указать число").printFrame("red");
                            }
                        }
                        break;
                    case("улучшить"):
                        if(arguments.length == 1 || arguments.length > 2) {
                            player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                        } else {
                            if(player.bonusPoints == 0) {
                                player.messageString += new Frame("У вас не осталось бонусных очков").printFrame("red");
                            } else {
                                String arg = arguments[1];
                                switch(arg) {
                                    case("Здоровье"):
                                        player.health += 5;
                                        player.bonusPoints--;
                                        player.messageString += new Frame("Здоровье увеличено").printFrame("dark blue");
                                        break;
                                    case("Выносливость"):
                                        player.stamina++;
                                        player.bonusPoints--;
                                        player.messageString += new Frame("Выносливость увеличена").printFrame("dark blue");
                                        break;
                                    case("Скорость"):
                                        if(player.speed == 0) {
                                            player.messageString += new Frame("Ваша скорость атаки максимальна").printFrame("red");
                                        } else {
                                            player.speed--;
                                            player.bonusPoints--;
                                            player.messageString += new Frame("Скорость атаки увеличена").printFrame("dark blue");
                                        }
                                        break;
                                    case("Урон"):
                                        player.attack += 2;
                                        player.bonusPoints--;
                                        player.messageString += new Frame("Урон увеличен").printFrame("dark blue");
                                        break;
                                    case("Защита"):
                                        player.armor += 2;
                                        player.bonusPoints--;
                                        player.messageString += new Frame("Защита увеличена").printFrame("dark blue");
                                        break;
                                    default:
                                        player.messageString += new Frame("Такого параметра нет").printFrame("red");
                                        break;
                                }
                            }
                        }
                        break;
                    case("чат"):
                        StringBuilder message = new StringBuilder();
                        for(int i = 1; i < arguments.length; i++) {
                            message.append(arguments[i]).append(" ");
                        }
                        for(int i = 0; i < Game.players.size(); i++) {
                            Game.players.get(i).messageString += new Frame("{ЧАТ} " + Color.setColor("yellow") + player.name + Color.setColor("white") + ": " + message.toString()).printFrame("green");
                        }
                        break;
                    case("список"):
                        if(player.place.name.equals("Главная площадь")) {
                            player.messageString += new Frame("Деньги: " + Color.setColor("yellow") + player.money + Color.setColor("white") + "\n" + Magazine.toString1()).printFrame("blue");
                        } else {
                            player.messageString += new Frame("Пройдите на " + Color.setColor("blue") + "Главную площадь" + Color.setColor("white") + ", чтобы пройти в магазин").printFrame("red");
                        }
                        break;
                    case("купить"):
                        if(player.place.name.equals("Главная площадь")) {
                            if(arguments.length == 1 || arguments.length > 2) {
                                player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                            } else {
                                try {
                                    int id = Integer.parseInt(arguments[1]);
                                    if(id < 0 || id >= Magazine.items.size() || Magazine.items.isEmpty()) {
                                        player.messageString += new Frame("Введен неправильный id предмета").printFrame("red");
                                    } else {
                                        player.inventory.add(Magazine.items.get(id));
                                        player.money -= Magazine.items.get(id).cost;
                                        player.messageString += new Frame("Куплено " + Magazine.items.get(id).shortName() + "за " + Color.setColor("yellow") + Magazine.items.get(id).cost + Color.setColor("white")).printFrame("blue");
                                        Magazine.items.remove(id);
                                    }
                                } catch(NumberFormatException e) {
                                    player.messageString += new Frame("Нужно ввести число").printFrame("red");
                                }
                            }
                        } else {
                            player.messageString += new Frame("Пройдите на " + Color.setColor("blue") + "Главную площадь" + Color.setColor("white") + ", чтобы покупать предметы").printFrame("red");
                        }
                        break;
                    case("продать"):
                        if(player.place.name.equals("Главная площадь")) {
                            if(arguments.length == 1 || arguments.length > 2) {
                                player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                            } else {
                                try {
                                    int id = Integer.parseInt(arguments[1]);
                                    if(id < 0 || id >= player.inventory.size() || player.inventory.isEmpty()) {
                                        player.messageString += new Frame("Введен неправильный id предмета").printFrame("red");
                                    } else {
                                        player.money += player.inventory.get(id).cost;
                                        Magazine.items.add(player.inventory.get(id));
                                        player.messageString += new Frame("продано " + player.inventory.get(id).shortName() + "за " + Color.setColor("yellow") + player.inventory.get(id).cost + Color.setColor("white")).printFrame("blue");
                                        player.inventory.remove(id);
                                    }
                                } catch(NumberFormatException e) {
                                    player.messageString += new Frame("Нужно ввести число").printFrame("red");
                                }
                            }
                        } else {
                            player.messageString += new Frame("Пройдите на " + Color.setColor("blue") + "Главную площадь" + Color.setColor("white") + ", чтобы продавать предметы").printFrame("red");
                        }
                        break;
                    case("атаковать"):
                        if(arguments.length == 1 || arguments.length > 2) {
                            player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                        } else {
                            try {
                                String arg = arguments[1];
                                int id = Integer.parseInt(arg);
                                if(id < 0 || id > player.place.enemies.size() || player.place.enemies.isEmpty()) {
                                    player.messageString += new Frame("Противника с таким id нет").printFrame("red");
                                } else {
                                    Game.fights.add(new Fight(player, player.place.enemies.get(id)));
                                    sleep(1500);
                                    Game.getFightByPlayerName(player.name).doFight();
                                }
                            } catch(NumberFormatException e) {
                                player.messageString += new Frame("Нужно ввести число").printFrame("red");
                            }
                        }
                        break;
                    case("обыскать"):
                        if(arguments.length == 1 || arguments.length > 2) {
                            player.messageString += new Frame("Неверное количество аргументов").printFrame("red");
                        } else {
                            try {
                                int id = Integer.parseInt(arguments[1]);
                                if(id < 0 || id >= player.place.deadBodies.size() || player.place.deadBodies.isEmpty()) {
                                    player.messageString += new Frame("Нет мертвого тела с таким id").printFrame("red");
                                } else {
                                    player.messageString += new Frame(player.getItems(player.place.deadBodies.get(id))).printFrame("dark blue");
                                    player.place.deadBodies.remove(id);
                                }
                            } catch (NumberFormatException e) {
                                player.messageString += new Frame("Нужно ввести число").printFrame("red");
                            }
                        }
                        break;
                    case("attack"):
                        Game.getFightByPlayerName(player.name).doFight();
                        sleep(1500);
                        break;
                    case("loose"):
                        player.messageString = new Frame("Вас убили").printFrame("red");
                        break;
                    case("помощь"):
                        StringBuilder str = new StringBuilder();
                        str.append(Color.setColor("green")).append("выход ").append(Color.setColor("white")).append("- выйти из игры\n")
                            .append(Color.setColor("green")).append("чат <сообщение> ").append(Color.setColor("white")).append("- написать сообщение в чат\n")
                            .append(Color.setColor("blue")).append("смотреть ").append(Color.setColor("white")).append("- посмотреть описание местности\n")
                            .append(Color.setColor("blue")).append("север ").append(Color.setColor("white")).append("- пойти на север\n")
                            .append(Color.setColor("blue")).append("юг ").append(Color.setColor("white")).append("- пойти на юг\n")
                            .append(Color.setColor("blue")).append("запад ").append(Color.setColor("white")).append("- пойти на запад\n")
                            .append(Color.setColor("blue")).append("восток ").append(Color.setColor("white")).append("- пойти на восток\n")
                            .append(Color.setColor("dark blue")).append("статус ").append(Color.setColor("white")).append("- показать статистику персонажа\n")
                            .append(Color.setColor("dark blue")).append("описание <номер> | <название умения> ").append(Color.setColor("white")).append("- описание предмета в инвентаре | умения\n")
                            .append(Color.setColor("dark blue")).append("улучшить <параметр> ").append(Color.setColor("white")).append("- улучшение параметра\n")
                            .append(Color.setColor("dark blue")).append("надеть <номер> ").append(Color.setColor("white")).append("- надеть предмет на персонажа\n")
                            .append(Color.setColor("dark blue")).append("обыскать <номер> ").append(Color.setColor("white")).append("- обыскать мертвое тело\n")
                            .append(Color.setColor("yellow")).append("список ").append(Color.setColor("white")).append("- вывести список товаров в магазине\n")
                            .append(Color.setColor("yellow")).append("купить <номер> ").append(Color.setColor("white")).append("- купить предмет в магазине\n")
                            .append(Color.setColor("yellow")).append("продать <номер> ").append(Color.setColor("white")).append("- продать предмет в магазине\n")
                            .append(Color.setColor("red")).append("атаковать <номер> ").append(Color.setColor("white")).append("- атаковать противника\n");
                        player.messageString += new Frame(str.toString()).printFrame("green");
                        break;
                    default:
                        player.messageString = new Frame("Неизвестная команда\nНаберите 'помощь' для вывода списка команд").printFrame("red");
                        break;
                }
                dos.writeUTF(player.messageString);//пишем
                dos.flush();
                player.messageString = "";
            }
        } catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}